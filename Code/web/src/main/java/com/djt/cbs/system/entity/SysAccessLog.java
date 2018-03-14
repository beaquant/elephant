package com.djt.cbs.system.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 系统操作日志。
 *
 * <p>Table: <strong>sys_access_log</strong>
 * <p><table class="er-mapping" cellspacing=0 cellpadding=0 style="border:solid 1 #666;padding:3px;">
 *   <tr style="background-color:#ddd;Text-align:Left;">
 *     <th nowrap>属性名</th><th nowrap>属性类型</th><th nowrap>字段名</th><th nowrap>字段类型</th><th nowrap>说明</th>
 *   </tr>
 *   <tr><td>logId</td><td>{@link Integer}</td><td>log_id</td><td>int</td><td>日志ID。</td></tr>
 *   <tr><td>userId</td><td>{@link Integer}</td><td>user_id</td><td>int</td><td>用户ID。</td></tr>
 *   <tr><td>userName</td><td>{@link String}</td><td>user_name</td><td>varchar</td><td>用户名。</td></tr>
 *   <tr><td>logType</td><td>{@link Integer}</td><td>log_type</td><td>int</td><td>日志类型。<br />1：操作日志；<br />2：用户登录日志；</td></tr>
 *   <tr><td>logTime</td><td>{@link Date}</td><td>log_time</td><td>datetime</td><td>日志时间。</td></tr>
 *   <tr><td>clientIp</td><td>{@link String}</td><td>client_ip</td><td>varchar</td><td>客户端IP。</td></tr>
 *   <tr><td>serverIp</td><td>{@link String}</td><td>server_ip</td><td>varchar</td><td>服务器IP。</td></tr>
 *   <tr><td>success</td><td>{@link Integer}</td><td>success</td><td>tinyint</td><td>是否成功。</td></tr>
 *   <tr><td>visitUrl</td><td>{@link String}</td><td>visit_url</td><td>varchar</td><td>访问的URL。</td></tr>
 *   <tr><td>refererUrl</td><td>{@link String}</td><td>referer_url</td><td>varchar</td><td>浏览器referer。</td></tr>
 *   <tr><td>clientToken</td><td>{@link String}</td><td>client_token</td><td>varchar</td><td>客户端标识。</td></tr>
 *   <tr><td>sessionId</td><td>{@link String}</td><td>session_id</td><td>varchar</td><td>登陆认证会话ID。</td></tr>
 *   <tr><td>paramValue</td><td>{@link String}</td><td>param_value</td><td>varchar</td><td>GET、POST的参数值。</td></tr>
 *   <tr><td>cookieValue</td><td>{@link String}</td><td>cookie_value</td><td>varchar</td><td>cookie值。</td></tr>
 *   <tr><td>agent</td><td>{@link String}</td><td>agent</td><td>varchar</td><td>浏览器agent。</td></tr>
 *   <tr><td>errorMsg</td><td>{@link String}</td><td>error_msg</td><td>text</td><td>异常消息。</td></tr>
 * </table>
 *
 */
public class SysAccessLog implements Serializable {
    /**
     *Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 897926294884805444L;

    /**
     * 登陆日志。
     */
    public static final int   LOG_TYPE_LOGIN   = 2;
    /**
     * 访问日志。
     */
    public static final int   LOG_TYPE_ACCESS  = 1;

    /**
     * 退出登陆日志
     */
    public static final int   LOG_TYPE_LOGOUT  = 3;

    private Integer           logId            = 0;

    /**
     * 获取 日志ID。
     */
    public Integer getLogId() {
        return this.logId;
    }

    /**
     * 设置 日志ID。
     *
     * @param value 属性值
     */
    public void setLogId(Integer value) {
        this.logId = value;
    }

    private Integer userId = 0;

    /**
     * 获取 用户ID。
     */
    public Integer getUserId() {
        return this.userId;
    }

    /**
     * 设置 用户ID。
     *
     * @param value 属性值
     */
    public void setUserId(Integer value) {
        this.userId = value;
    }

    private String userName = "";

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String value) {
        this.userName = value;
    }

    private Integer logType = 1;

    /**
     * 获取 日志类型。
     *
     * <p>
     * 1：操作日志；<br />
     * 2：用户登录日志；<br />
     * 3：退出登陆；
     */
    public Integer getLogType() {
        return this.logType;
    }

    /**
     * 设置 日志类型。
     *
     * <p>
     * 1：操作日志；<br />
     * 2：用户登录日志；<br />
     * 3：退出登陆；
     * @param value 属性值
     */
    public void setLogType(Integer value) {
        this.logType = value;
    }

    private Date logTime = null;

    /**
     * 获取 日志时间。
     */
    public Date getLogTime() {
        return this.logTime;
    }

    /**
     * 设置 日志时间。
     *
     * @param value 属性值
     */
    public void setLogTime(Date value) {
        this.logTime = value;
    }

    private String clientIp = "";

    /**
     * 获取 客户端IP。
     */
    public String getClientIp() {
        return this.clientIp;
    }

    /**
     * 设置 客户端IP。
     *
     * @param value 属性值
     */
    public void setClientIp(String value) {
        this.clientIp = value;
    }

    private String serverIp = "";

    /**
     * 获取 服务器IP。
     */
    public String getServerIp() {
        return this.serverIp;
    }

    /**
     * 设置 服务器IP。
     *
     * @param value 属性值
     */
    public void setServerIp(String value) {
        this.serverIp = value;
    }

    private Integer success = 1;

    /**
     * 获取 是否成功。
     */
    public Integer getSuccess() {
        return this.success;
    }

    /**
     * 设置 是否成功。
     *
     * @param value 属性值
     */
    public void setSuccess(Integer value) {
        this.success = value;
    }

    private String visitUrl = "";

    /**
     * 获取 访问的URL。
     */
    public String getVisitUrl() {
        return this.visitUrl;
    }

    /**
     * 设置 访问的URL。
     *
     * @param value 属性值
     */
    public void setVisitUrl(String value) {
        this.visitUrl = value;
    }

    private String refererUrl = "";

    /**
     * 获取 浏览器referer。
     */
    public String getRefererUrl() {
        return this.refererUrl;
    }

    /**
     * 设置 浏览器referer。
     *
     * @param value 属性值
     */
    public void setRefererUrl(String value) {
        this.refererUrl = value;
    }

    private String clientToken = "";

    public String getClientToken() {
        return this.clientToken;
    }

    public void setClientToken(String value) {
        this.clientToken = value;
    }

    private String sessionId = "";

    public String getSessionId() {
        return this.sessionId;
    }

    public void setSessionId(String value) {
        this.sessionId = value;
    }

    private String paramValue = "";

    public String getParamValue() {
        return this.paramValue;
    }

    public void setParamValue(String value) {
        this.paramValue = value;
    }

    private String cookieValue = "";

    public String getCookieValue() {
        return this.cookieValue;
    }

    public void setCookieValue(String value) {
        this.cookieValue = value;
    }

    private String agent = "";

    /**
     * 获取 浏览器agent。
     */
    public String getAgent() {
        return this.agent;
    }

    /**
     * 设置 浏览器agent。
     *
     * @param value 属性值
     */
    public void setAgent(String value) {
        this.agent = value;
    }

    private String errorMsg;

    /**
     * 获取 异常消息。
     */
    public String getErrorMsg() {
        return this.errorMsg;
    }

    /**
     * 设置 异常消息。
     *
     * @param value 属性值
     */
    public void setErrorMsg(String value) {
        this.errorMsg = value;
    }

}