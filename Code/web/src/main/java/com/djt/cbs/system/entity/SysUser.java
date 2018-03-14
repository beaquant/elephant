package com.djt.cbs.system.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 系统用户。
 *
 * <p>Table: <strong>sys_user</strong>
 * <p><table class="er-mapping" cellspacing=0 cellpadding=0 style="border:solid 1 #666;padding:3px;">
 *   <tr style="background-color:#ddd;Text-align:Left;">
 *     <th nowrap>属性名</th><th nowrap>属性类型</th><th nowrap>字段名</th><th nowrap>字段类型</th><th nowrap>说明</th>
 *   </tr>
 *   <tr><td>userId</td><td>{@link Integer}</td><td>user_id</td><td>int</td><td>&nbsp;</td></tr>
 *   <tr><td>userName</td><td>{@link String}</td><td>user_name</td><td>varchar</td><td>&nbsp;</td></tr>
 *   <tr><td>status</td><td>{@link Integer}</td><td>status</td><td>int</td><td>&nbsp;</td></tr>
 *   <tr><td>loginId</td><td>{@link String}</td><td>login_id</td><td>varchar</td><td>&nbsp;</td></tr>
 *   <tr><td>password</td><td>{@link String}</td><td>password</td><td>varchar</td><td>&nbsp;</td></tr>
 *   <tr><td>email</td><td>{@link String}</td><td>email</td><td>varchar</td><td>&nbsp;</td></tr>
 *   <tr><td>phone</td><td>{@link String}</td><td>phone</td><td>varchar</td><td>&nbsp;</td></tr>
 *   <tr><td>mobile</td><td>{@link String}</td><td>mobile</td><td>varchar</td><td>&nbsp;</td></tr>
 *   <tr><td>createUser</td><td>{@link String}</td><td>create_user</td><td>varchar</td><td>创建者。</td></tr>
 *   <tr><td>createTime</td><td>{@link Date}</td><td>create_time</td><td>datetime</td><td>创建时间。</td></tr>
 *   <tr><td>updateUser</td><td>{@link String}</td><td>update_user</td><td>varchar</td><td>最后更新人。</td></tr>
 *   <tr><td>updateTime</td><td>{@link Date}</td><td>update_time</td><td>timestamp/date</td><td>最后更新时间。</td></tr>
 * </table>
 *
 */
public class SysUser implements Serializable {
    /**
     *Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -7987745453662526261L;

    private Integer           userId;

    public Integer getUserId() {
        return this.userId;
    }

    public void setUserId(Integer value) {
        this.userId = value;
    }

    private String userName;

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String value) {
        this.userName = value;
    }

    private Integer status = 1;

    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(Integer value) {
        this.status = value;
    }

    private String loginId;

    public String getLoginId() {
        return this.loginId;
    }

    public void setLoginId(String value) {
        this.loginId = value;
    }

    private String password;

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String value) {
        this.password = value;
    }

    private String email;

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String value) {
        this.email = value;
    }

    private String phone;

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String value) {
        this.phone = value;
    }

    private String mobile;

    public String getMobile() {
        return this.mobile;
    }

    public void setMobile(String value) {
        this.mobile = value;
    }

    private String createUser = "";

    /**
     * 获取 创建者。
     */
    public String getCreateUser() {
        return this.createUser;
    }

    /**
     * 设置 创建者。
     *
     * @param value 属性值
     */
    public void setCreateUser(String value) {
        this.createUser = value;
    }

    private Date createTime = null;

    /**
     * 获取 创建时间。
     */
    public Date getCreateTime() {
        return this.createTime;
    }

    /**
     * 设置 创建时间。
     *
     * @param value 属性值
     */
    public void setCreateTime(Date value) {
        this.createTime = value;
    }

    private String updateUser = "";

    /**
     * 获取 最后更新人。
     */
    public String getUpdateUser() {
        return this.updateUser;
    }

    /**
     * 设置 最后更新人。
     *
     * @param value 属性值
     */
    public void setUpdateUser(String value) {
        this.updateUser = value;
    }

    private Date updateTime;

    /**
     * 获取 最后更新时间。
     */
    public Date getUpdateTime() {
        return this.updateTime;
    }

    /**
     * 设置 最后更新时间。
     *
     * @param value 属性值
     */
    public void setUpdateTime(Date value) {
        this.updateTime = value;
    }

}