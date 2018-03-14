package com.djt.cbs.web.controller.system;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.djt.cbs.system.entity.SysAction;
import com.djt.cbs.system.service.SystemService;
import com.djt.cbs.web.util.HttpJsonResult;
import com.djt.cbs.web.util.WebUtil;
import com.djt.common.PagerInfo;
import com.djt.common.ServiceResult;

/**
 * 系统管理功能
 */
@Controller
@RequestMapping("/api/sys/action")
public class ActionController {
    private static org.apache.log4j.Logger log = org.apache.log4j.LogManager
                                                   .getLogger(ActionController.class);
    @Resource(name = "systemService")
    SystemService                          systemService;

    @RequestMapping(value = { "/find" }, method = { RequestMethod.GET })
    public @ResponseBody
    HttpJsonResult<List<SysAction>> findAction(@RequestParam(required = false) String actKey,
                                               @RequestParam(required = false) String actName,
                                               @RequestParam(required = false) String remark,
                                               @RequestParam(required = false) Integer status,
                                               @RequestParam(required = false) String menuName,
                                               @RequestParam(required = false) Integer pi,
                                               @RequestParam(required = false) Integer ps) {
        HttpJsonResult<List<SysAction>> result = new HttpJsonResult<List<SysAction>>();
        try {
            PagerInfo pager = null;
            if (pi != null && pi > 0 && ps != null && ps > 0)
                pager = new PagerInfo(ps, pi);
            ServiceResult<List<SysAction>> actionResult = systemService.findAction(actKey, actName,
                remark, status, menuName, pager);
            if (actionResult.getPager() != null)
                result.setTotalCount(actionResult.getPager().getRowsCount());
            result.setData(actionResult.getResult());
        } catch (Exception e) {
            log.error("查询操作列表异常", e);
            result.setMessage(e.getMessage());
        }

        return result;
    }

    private HttpJsonResult<SysAction> internalGetAction(Integer actId) {
        try {
            ServiceResult<SysAction> serviceResult = systemService.getActionById(actId);
            if (!serviceResult.getSuccess())
                return new HttpJsonResult<SysAction>(serviceResult.getMessage());

            return new HttpJsonResult<SysAction>(serviceResult.getResult());
        } catch (Exception e) {
            log.error("获取操作异常:", e);
            return new HttpJsonResult<SysAction>(e.getMessage());
        }
    }

    @RequestMapping(value = { "/get/{actionId}" }, method = { RequestMethod.GET })
    public @ResponseBody
    HttpJsonResult<SysAction> getAction(@PathVariable Integer actId) {
        return this.internalGetAction(actId);
    }

    @RequestMapping(value = { "/update" }, method = { RequestMethod.POST })
    public @ResponseBody
    HttpJsonResult<Boolean> updateAction(SysAction action, HttpServletRequest request) {
        try {
            ServiceResult<Boolean> result = this.systemService.updateAction(action,
                WebUtil.currentUserId(request));
            if (!result.getSuccess())
                return new HttpJsonResult<Boolean>(result.getMessage());
            return new HttpJsonResult<Boolean>(result.getResult());
        } catch (Exception e) {
            log.error("更新操作异常", e);
            return new HttpJsonResult<Boolean>(e.getMessage());
        }
    }

    @RequestMapping(value = { "/create" }, method = { RequestMethod.POST })
    public @ResponseBody
    HttpJsonResult<SysAction> createUser(SysAction action, HttpServletRequest request) {
        try {
            ServiceResult<Integer> result = this.systemService.createAction(action,
                WebUtil.currentUserId(request));
            if (!result.getSuccess())
                return new HttpJsonResult<SysAction>(result.getMessage());
            return this.internalGetAction(result.getResult());
        } catch (Exception e) {
            log.error("创建操作异常", e);
            return new HttpJsonResult<SysAction>(e.getMessage());
        }
    }
}