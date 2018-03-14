package com.djt.cbs.web.controller.system;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.djt.cbs.system.entity.SysUser;
import com.djt.cbs.system.service.SystemService;
import com.djt.cbs.web.util.HttpJsonResult;
import com.djt.cbs.web.util.PasswordRegexCheckUtil;
import com.djt.cbs.web.util.WebUtil;
import com.djt.common.PagerInfo;
import com.djt.common.ServiceResult;
import com.djt.common.util.StringUtil;

/**
 * 系统管理功能
 */
@Controller
@RequestMapping("/api/sys/user")
public class UserController {
    private static org.apache.log4j.Logger log = org.apache.log4j.LogManager
                                                   .getLogger(UserController.class);

    @Resource(name = "systemService")
    SystemService                          systemService;

    @RequestMapping(value = { "/find" }, method = { RequestMethod.GET })
    public @ResponseBody
    HttpJsonResult<List<SysUser>> findUser(@RequestParam(required = false) String name,
                                           @RequestParam(required = false) String loginId,
                                           @RequestParam(required = false) Integer status,
                                           @RequestParam(required = false) Integer pi,
                                           @RequestParam(required = false) Integer ps) {
        HttpJsonResult<List<SysUser>> result = new HttpJsonResult<List<SysUser>>();
        try {
            PagerInfo pager = null;
            if (pi != null && pi > 0 && ps != null && ps > 0)
                pager = new PagerInfo(ps, pi);
            ServiceResult<List<SysUser>> userResult = systemService.findUser(name, loginId, status,
                pager);
            if (!userResult.getSuccess()) {
                result.setMessage(userResult.getMessage());
                return result;
            }
            //屏蔽用户密码
            if (userResult.getResult() != null) {
                for (SysUser user : userResult.getResult()) {
                    user.setPassword(WebUtil.PASSWORD_MASK);
                }
            }
            if (userResult.getPager() != null)
                result.setTotalCount(userResult.getPager().getRowsCount());
            result.setData(userResult.getResult());
        } catch (Exception e) {
            log.error("查询用户列表异常", e);
            result.setMessage(e.getMessage());
        }

        return result;
    }

    private HttpJsonResult<SysUser> internalGetUser(Integer userId) {
        try {
            //取用户资料
            ServiceResult<SysUser> serviceResult = systemService.getUserById(userId);
            if (!serviceResult.getSuccess())
                return new HttpJsonResult<SysUser>(serviceResult.getMessage());

            return new HttpJsonResult<SysUser>(serviceResult.getResult());
        } catch (Exception e) {
            log.error("获取用户异常:", e);
            return new HttpJsonResult<SysUser>(e.getMessage());
        }
    }

    @RequestMapping(value = { "/get/{userId}" }, method = { RequestMethod.GET })
    public @ResponseBody
    HttpJsonResult<SysUser> getUser(@PathVariable Integer userId) {
        return this.internalGetUser(userId);
    }

    @RequestMapping(value = { "/update" }, method = { RequestMethod.POST })
    public @ResponseBody
    HttpJsonResult<Boolean> updateUser(SysUser user) {
        HttpJsonResult<Boolean> result = new HttpJsonResult<Boolean>();
        try {
            if (StringUtil.isEmpty(user.getPassword(), true)) {
                result.setMessage("密码不能为空");
                return result;
            }
            //如果密码值等于屏蔽码，说明没有更改
            if (WebUtil.PASSWORD_MASK.equals(user.getPassword().trim()))
                user.setPassword(null);

            //校验密码是否合法:密码长度要超过4位，并且只能是数字和字母
            if (!StringUtils.isBlank(user.getPassword())) {
                if (!PasswordRegexCheckUtil.isPasswordCheckOK(user.getPassword())) {
                    result.setMessage("密码长度要超过4位，并且只能是数字和字母。");
                    return result;
                }
            }

            //TODO: 取当前操作用户ID
            ServiceResult<Boolean> srvResult = this.systemService.updateUser(user, 15);
            if (!srvResult.getSuccess()) {
                result.setMessage(result.getMessage());
                return result;
            }
            return result;
        } catch (Exception e) {
            log.error("更新用户异常", e);
            result.setMessage("更新用户异常：" + e.getMessage());
            return result;
        }
    }

    @RequestMapping(value = { "/create" }, method = { RequestMethod.POST })
    public @ResponseBody
    HttpJsonResult<SysUser> createUser(SysUser user) {
        try {
            //TODO: 取当前操作用户ID
            ServiceResult<Integer> result = this.systemService.createUser(user, 12);
            if (!result.getSuccess())
                return new HttpJsonResult<SysUser>(result.getMessage());
            return this.internalGetUser(result.getResult());
        } catch (Exception e) {
            log.error("创建用户异常", e);
            return new HttpJsonResult<SysUser>(e.getMessage());
        }
    }
}