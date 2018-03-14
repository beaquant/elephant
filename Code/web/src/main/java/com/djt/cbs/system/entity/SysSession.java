package com.djt.cbs.system.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户登录会话状态。
 *
 * <p>Table: <strong>sys_session</strong>
 * <p><table class="er-mapping" cellspacing=0 cellpadding=0 style="border:solid 1 #666;padding:3px;">
 *   <tr style="background-color:#ddd;Text-align:Left;">
 *     <th nowrap>属性名</th><th nowrap>属性类型</th><th nowrap>字段名</th><th nowrap>字段类型</th><th nowrap>说明</th>
 *   </tr>
 *   <tr><td>sessionId</td><td>{@link String}</td><td>session_id</td><td>varchar</td><td>会话ID。</td></tr>
 *   <tr><td>userId</td><td>{@link Integer}</td><td>user_id</td><td>int</td><td>用户ID。</td></tr>
 *   <tr><td>userName</td><td>{@link String}</td><td>user_name</td><td>varchar</td><td>用户姓名。</td></tr>
 *   <tr><td>loginTime</td><td>{@link Date}</td><td>login_time</td><td>datetime</td><td>登录时间。</td></tr>
 *   <tr><td>lastAccessTime</td><td>{@link Date}</td><td>last_access_time</td><td>datetime</td><td>最后访问时间。</td></tr>
 *   <tr><td>loginIp</td><td>{@link String}</td><td>login_ip</td><td>varchar</td><td>客户端IP。</td></tr>
 * </table>
 *
 */
public class SysSession implements Serializable {
    /**
     *Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 914818167378928476L;

    private String            sessionId        = "";

    /**
     * 获取 会话ID。
     */
    public String getSessionId() {
        return this.sessionId;
    }

    /**
     * 设置 会话ID。
     *
     * @param value 属性值
     */
    public void setSessionId(String value) {
        this.sessionId = value;
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

    /**
     * 获取 用户姓名。
     */
    public String getUserName() {
        return this.userName;
    }

    /**
     * 设置 用户姓名。
     *
     * @param value 属性值
     */
    public void setUserName(String value) {
        this.userName = value;
    }

    private Date loginTime = null;

    /**
     * 获取 登录时间。
     */
    public Date getLoginTime() {
        return this.loginTime;
    }

    /**
     * 设置 登录时间。
     *
     * @param value 属性值
     */
    public void setLoginTime(Date value) {
        this.loginTime = value;
    }

    private Date lastAccessTime = null;

    /**
     * 获取 最后访问时间。
     */
    public Date getLastAccessTime() {
        return this.lastAccessTime;
    }

    /**
     * 设置 最后访问时间。
     *
     * @param value 属性值
     */
    public void setLastAccessTime(Date value) {
        this.lastAccessTime = value;
    }

    private String loginIp;

    /**
     * 获取 登录IP
     * @return
     */
    public String getLoginIp() {
        return loginIp;
    }

    /**
     * 设置登录IP
     * @param loginIp
     */
    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

}