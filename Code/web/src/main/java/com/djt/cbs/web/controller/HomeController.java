package com.djt.cbs.web.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.WebUtils;

import com.djt.cbs.system.entity.SysAccessLog;
import com.djt.cbs.system.entity.SysUser;
import com.djt.cbs.system.service.SystemService;
import com.djt.cbs.web.util.HttpJsonResult;
import com.djt.cbs.web.util.PasswordRegexCheckUtil;
import com.djt.cbs.web.util.RandomValidateCodeGenerate;
import com.djt.cbs.web.util.WebUtil;
import com.djt.common.BusinessException;
import com.djt.common.PagerInfo;
import com.djt.common.ServiceResult;
import com.djt.common.util.ConvertUtil;
import com.djt.common.util.DateUtil;
import com.djt.common.util.EncryptUtil;
import com.djt.common.util.StringUtil;

@Controller
public class HomeController {
    private static org.apache.log4j.Logger log = org.apache.log4j.LogManager
                                                   .getLogger(HomeController.class);

    @Resource(name = "systemService")
    SystemService                          systemService;

    @RequestMapping(value = { "/index.html" }, method = { RequestMethod.GET })
    public String index(HttpServletRequest request, ModelMap model) {
        model.addAttribute("pageSize", WebUtil.readCookie(request, WebUtil.DEFAULT_PAGE_SIZE_NAME));

        String userName = WebUtil.readCookie(request, WebUtil.CBS_USER_NAME);

        if (!StringUtil.isEmpty(userName)) {
            userName = EncryptUtil.fromBASE64(userName);
        }

        if (StringUtil.isEmpty(userName)) {
            userName = "管理员";
        }

        model.addAttribute("userName", userName);
        return "index";
    }

    @RequestMapping(value = { "/main.html" }, method = { RequestMethod.GET })
    public String main(HttpServletRequest request, ModelMap model) {
        String userName = WebUtil.readCookie(request, WebUtil.CBS_USER_NAME);

        if (!StringUtil.isEmpty(userName)) {
            userName = EncryptUtil.fromBASE64(userName);
        }

        if (StringUtil.isEmpty(userName)) {
            userName = "管理员";
        }

        model.addAttribute("userName", userName);
        return "main";
    }

    @RequestMapping(value = { "/", "/login", "/login.html" }, method = { RequestMethod.GET })
    public String login(HttpServletRequest request, ModelMap model) {
        return "login";
        //        UserAgent useragent = new UserAgent(request.getHeader("user-agent"));
        //        DeviceType dt = useragent.getOperatingSystem().getDeviceType();
        //        if (DeviceType.MOBILE.equals(dt)) {//匹配移动设备登录界面
        //            return "login_for_mobile";
        //        } else {
        //            return "login";
        //        }
    }

    @RequestMapping(value = { "/login_m.html" }, method = { RequestMethod.GET })
    public String loginForMobile(HttpServletRequest request, ModelMap model) {
        return "login_for_mobile";
    }

    @RequestMapping(value = { "/api/sys/login" }, method = { RequestMethod.POST })
    public @ResponseBody
    HttpJsonResult<Boolean> login(@RequestParam(required = true) String loginId,
                                  @RequestParam(required = true) String password,
                                  @RequestParam(required = true) String randomCode,
                                  HttpServletRequest request, HttpServletResponse response) {
        HttpJsonResult<Boolean> result = new HttpJsonResult<Boolean>();
        try {
            Assert.notNull(this.systemService, "Property 'systemService' is required!");
            //增加验证码逻辑
            String randomString = WebUtil.readCookie(request,
                RandomValidateCodeGenerate.RANDOMCODEKEY);
            if (StringUtils.isBlank(randomCode) || !randomCode.equalsIgnoreCase(randomString)) {
                log.info("[sys][login] 用户登陆失败,验证码不正确：loginId:" + loginId);
                result.setMessage("请输入正确的验证码");
                WebUtil.saveLoginLog(request, SysAccessLog.LOG_TYPE_LOGIN, 0, loginId, false,
                    "验证码不正确");
                return result;
            }

            String sessionId = UUID.randomUUID().toString();
            ServiceResult<SysUser> loginResult = this.systemService.userLoginByClientIp(loginId,
                password, sessionId, WebUtil.clientIp(request));
            if (!loginResult.getSuccess()) {
                log.info("[sys][login] 用户登陆失败：loginId:" + loginId);
                result.setMessage(loginResult.getMessage());
                WebUtil.saveLoginLog(request, SysAccessLog.LOG_TYPE_LOGIN, 0, loginId, false,
                    loginResult.getMessage());
                return result;
            }
            //登陆成功，写cookie
            WebUtil.writeAuthCookie(response, sessionId, loginResult.getResult());
            //记录登陆日志
            WebUtil.saveLoginLog(request, SysAccessLog.LOG_TYPE_LOGIN, loginResult.getResult()
                .getUserId(), loginResult.getResult().getUserName(), true, null);
            //设置默认分页大小
            int pageSize = ConvertUtil.toInt(
                WebUtil.readCookie(request, WebUtil.DEFAULT_PAGE_SIZE_NAME), 0);
            if (pageSize <= 0) {
                pageSize = WebUtil.computerPageSize(ConvertUtil.toInt(
                    request.getParameter("winHeight"), 0));
                WebUtil.writeCookie(response, WebUtil.DEFAULT_PAGE_SIZE_NAME, pageSize + "",
                    1 * 365 * 24 * 3600);
                if (log.isInfoEnabled())
                    log.info("User '" + loginResult.getResult().getUserName()
                             + "' login, scree height:" + request.getParameter("winHeight")
                             + ", set default page size to:" + pageSize);
            }
        } catch (BusinessException be) {
            result.setMessage(be.getMessage());
            WebUtil.saveLoginLog(request, SysAccessLog.LOG_TYPE_LOGIN, 0, loginId, false,
                be.getMessage());
        } catch (Exception e) {
            log.error("[sys][login] 登陆异常", e);
            result.setMessage("登陆时系统发生未知异常：" + e.getMessage());
            WebUtil.saveLoginLog(request, SysAccessLog.LOG_TYPE_LOGIN, 0, loginId, false,
                e.getMessage());
        }
        //登陆成功清除验证码session
        WebUtil.deleteCookie(response, RandomValidateCodeGenerate.RANDOMCODEKEY);
        return result;
    }

    @RequestMapping(value = { "/api/sys/logout" }, method = { RequestMethod.GET })
    public @ResponseBody
    HttpJsonResult<Boolean> logout(HttpServletRequest request, HttpServletResponse response) {
        HttpJsonResult<Boolean> result = new HttpJsonResult<Boolean>();
        try {
            if (!WebUtil.checkLogin(request))
                return result;
            Assert.notNull(this.systemService, "Property 'systemService' is required!");
            //退出登陆，删除会话数据
            this.systemService.userLogout(WebUtil.sessionId(request));
            //记录登陆退出日志
            WebUtil.saveLoginLog(request, SysAccessLog.LOG_TYPE_LOGOUT, 0, null, true, null);
            //删除登陆认证cookie
            WebUtil.deleteAuthCookie(response);
        } catch (BusinessException be) {
            result.setMessage(be.getMessage());
            WebUtil.saveLoginLog(request, SysAccessLog.LOG_TYPE_LOGOUT, 0, null, false,
                be.getMessage());
        } catch (Exception e) {
            log.error("[sys][login] 退出登陆异常", e);
            result.setMessage("退出登陆时系统发生未知异常：" + e.getMessage());
            WebUtil.saveLoginLog(request, SysAccessLog.LOG_TYPE_LOGOUT, 0, null, false,
                e.getMessage());
        }

        return result;
    }

    @RequestMapping(value = { "/api/sys/changePwd" }, method = { RequestMethod.POST })
    public @ResponseBody
    HttpJsonResult<Boolean> changePwd(HttpServletRequest request, HttpServletResponse response) {
        HttpJsonResult<Boolean> result = new HttpJsonResult<Boolean>();
        String[] userInfo = EncryptUtil.fromBASE64(
            WebUtil.readCookie(request, WebUtil.CBS_USER_INFO_NAME)).split(";");
        try {
            Assert.notNull(this.systemService, "Property 'systemService' is required!");

            String oldPassword = WebUtils.findParameterValue(request, "oldPassword");
            String newPassword = WebUtils.findParameterValue(request, "newPassword");
            //校验密码是否合法:密码长度要超过4位，并且只能是数字和字母
            if (!PasswordRegexCheckUtil.isPasswordCheckOK(newPassword)) {
                log.info("[sys] 用户修改密码失败：userId:" + userInfo[0] + "。失败原因：密码不够4位或不是数字和字母");
                result.setMessage("密码长度要超过4位，并且只能是数字和字母。");
                return result;
            }
            ServiceResult<Boolean> serviceResult = this.systemService.changePwd(
                Integer.parseInt(userInfo[0]), oldPassword, newPassword);

            if (!serviceResult.getSuccess()) {
                log.info("[sys] 用户修改密码失败：userId:" + userInfo[0] + "。失败原因："
                         + serviceResult.getMessage());
                result.setMessage(serviceResult.getMessage());
                return result;
            }
        } catch (BusinessException be) {
            result.setMessage(be.getMessage());
            WebUtil.saveAccessLog(request, be.getMessage());
        } catch (Exception e) {
            log.error("[sys] 修改密码异常", e);
            result.setMessage("修改密码时系统发生未知异常：" + e.getMessage());
            WebUtil.saveAccessLog(request, e.getMessage());
        }

        return result;
    }

    @RequestMapping(value = { "/api/sys/accesslog/find" }, method = { RequestMethod.GET })
    public @ResponseBody
    HttpJsonResult<List<SysAccessLog>> findAccessLog(@RequestParam(required = true) Date startTime,
                                                     @RequestParam(required = true) Date endTime,
                                                     @RequestParam(required = false) String userName,
                                                     @RequestParam(required = false) String visitUrl,
                                                     @RequestParam(required = false) String clientIp,
                                                     @RequestParam(required = false) String sessionId,
                                                     @RequestParam(required = true) Integer pi,
                                                     @RequestParam(required = true) Integer ps) {
        HttpJsonResult<List<SysAccessLog>> result = new HttpJsonResult<List<SysAccessLog>>();
        try {
            PagerInfo pager = null;
            if (pi != null && pi > 0 && ps != null && ps > 0)
                pager = new PagerInfo(ps, pi);
            endTime = DateUtil.add(endTime, Calendar.DAY_OF_MONTH, 1);
            ServiceResult<List<SysAccessLog>> srvResult = systemService.findAccessLog(startTime,
                endTime, userName, visitUrl, clientIp, sessionId, pager);
            if (!srvResult.getSuccess()) {
                result.setMessage(srvResult.getMessage());
                return result;
            }
            if (srvResult.getPager() != null)
                result.setTotalCount(srvResult.getPager().getRowsCount());
            result.setData(srvResult.getResult());
        } catch (Exception e) {
            log.error("查询系统操作日志异常", e);
            result.setMessage(e.getMessage());
        }

        return result;
    }
}