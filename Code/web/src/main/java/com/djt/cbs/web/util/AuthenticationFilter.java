package com.djt.cbs.web.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.djt.common.util.JsonUtil;
import com.djt.common.util.StringUtil;

/**
 * 权限校验、记录访问日志。                      
 * @Filename: AuthenticationFilter.java
 * @Version: 1.0
 * @Author: rongmai 刘志斌
 * @Email: rongmai@mbaobao.com
 */
public class AuthenticationFilter implements Filter {
    private static org.apache.log4j.Logger log      = org.apache.log4j.LogManager
                                                        .getLogger(AuthenticationFilter.class);

    private Set<String>                    excludes = new HashSet<String>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String excludesString = filterConfig.getInitParameter("excludes");
        if (!StringUtil.isEmpty(excludesString)) {
            String[] tokens = excludesString.split(";|,|\\|");
            for (String s : tokens) {
                excludes.add(StringUtil.trim(s).toLowerCase());
            }
        }
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
                                                                                             throws IOException,
                                                                                             ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        //确保cbs-client-token cookie有值
        WebUtil.clientToken(req, res);
        if (this.excludes.contains(req.getRequestURI())) {
            chain.doFilter(request, response);
            return;
        }

        //登陆状态验证
        if (!WebUtil.checkLogin(req)) {
            WebUtil.saveAccessLog(req, "未登陆访问");
            this.illegalAccessRedirect(req, res, "您尚未登陆系统，请登陆后再进行该操作");
            return;
        }

        //已经登陆，权限验证
        HttpJsonResult<Boolean> checkResult = WebUtil.checkPermission(req);
        if (checkResult.getSuccess()) {
            try {
                chain.doFilter(request, response);
                //有权限访问
                WebUtil.saveAccessLog(req);
            } catch (Exception e) {
                log.error("[sys][log]访问异常：" + req.getRequestURI(), e);
                WebUtil.saveAccessLog(req, "服务器异常：" + e.getMessage());
                errorRedirect(req, res, e.getMessage());
            }
            return;
        } else {
            //无权限访问
            WebUtil.saveAccessLog(req, "未授权访问");
            this.illegalAccessRedirect(req, res, checkResult.getMessage());
            return;
        }
    }

    private void errorRedirect(HttpServletRequest req, HttpServletResponse res, String message) {
        //ajax请求
        PrintWriter writer = null;
        try {
            writer = res.getWriter();
            String output = "";
            if (req.getRequestURI().startsWith("/api/")) {
                HttpJsonResult<Boolean> result = new HttpJsonResult<Boolean>();
                result.setMessage("服务器发生未知错误：" + message);
                output = JsonUtil.toJson(result);
            } else {
                output = "服务器发生未知错误：<br />" + message;
            }
            writer.write(output);
        } catch (Exception e) {
            log.warn("[sys][login]", e);
        } finally {
            if (writer != null)
                writer.close();
        }
    }

    private void illegalAccessRedirect(HttpServletRequest req, HttpServletResponse res,
                                       String message) {
        //ajax请求
        if (req.getRequestURI().startsWith("/api/") || "您不能执行该操作：没有权限".equalsIgnoreCase(message)
            || req.getRequestURI().endsWith("/quick_signup.html")) {
            PrintWriter writer = null;
            try {
                res.setCharacterEncoding("utf-8");
                res.setContentType("text/json");
                HttpJsonResult<Boolean> result = new HttpJsonResult<Boolean>();
                result.setMessage(message);
                writer = res.getWriter();
                writer.write(JsonUtil.toJson(result));
            } catch (Exception e) {
                log.warn("[sys][login]", e);
            } finally {
                if (writer != null)
                    writer.close();
            }
            return;
        }
        //其他请求
        try {
            //res.sendRedirect("/login.html");
            res.setContentType("text/html");
            PrintWriter out = res.getWriter();
            out.println("<html>");
            out.println("<script>");
            out.println("window.open ('/login.html','_top')");
            out.println("</script>");
            out.println("</html>");
            return;
        } catch (Exception e) {
            log.warn("[sys][perm]检测到未授权的访问，redirect时出错", e);
        }
        return;
    }

    @Override
    public void destroy() {
    }

}