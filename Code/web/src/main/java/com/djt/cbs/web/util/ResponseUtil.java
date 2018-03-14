package com.djt.cbs.web.util;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

public class ResponseUtil {
    private static org.apache.log4j.Logger log = org.apache.log4j.LogManager
                                                   .getLogger(ResponseUtil.class);

    /**
     * 缓冲json串
     * @param response
     * @param success
     * @param message
     */
    public static void flushJson(HttpServletResponse response, Boolean success, String message) {
        String responseData = "{\"success\":" + success + ", \"error_message\":\"" + message
                              + "\" }";
        //返回响应数据
        try {
            response.getWriter().write(responseData);
            response.getWriter().flush();
            response.getWriter().close();
        } catch (IOException e) {
            log.error(e);
        }
    }

    /**
     * 缓冲json串
     * @param response
     * @param json
     */
    public static void flushJson(HttpServletResponse response, String json) {
        //返回响应数据
        try {
            response.getWriter().write(json);
            response.getWriter().flush();
            response.getWriter().close();
        } catch (IOException e) {
            log.error(e);
        }
    }
}
