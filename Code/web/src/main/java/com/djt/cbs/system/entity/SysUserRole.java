package com.djt.cbs.system.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>Table: <strong>sys_user_role</strong>
 * <p><table class="er-mapping" cellspacing=0 cellpadding=0 style="border:solid 1 #666;padding:3px;">
 *   <tr style="background-color:#ddd;Text-align:Left;">
 *     <th nowrap>属性名</th><th nowrap>属性类型</th><th nowrap>字段名</th><th nowrap>字段类型</th><th nowrap>说明</th>
 *   </tr>
 *   <tr><td>userRoleId</td><td>{@link Integer}</td><td>user_role_id</td><td>int</td><td>无意义自增ID。</td></tr>
 *   <tr><td>userId</td><td>{@link Integer}</td><td>user_id</td><td>int</td><td>&nbsp;</td></tr>
 *   <tr><td>roleId</td><td>{@link Integer}</td><td>role_id</td><td>int</td><td>角色ID。</td></tr>
 *   <tr><td>startTime</td><td>{@link Date}</td><td>start_time</td><td>datetime</td><td>开始时间。</td></tr>
 *   <tr><td>endTime</td><td>{@link Date}</td><td>end_time</td><td>datetime</td><td>结束时间。</td></tr>
 *   <tr><td>updateUser</td><td>{@link String}</td><td>update_user</td><td>varchar</td><td>最后更新人。</td></tr>
 *   <tr><td>updateTime</td><td>{@link Date}</td><td>update_time</td><td>timestamp/date</td><td>最后更新时间。</td></tr>
 * </table>
 *
 */
public class SysUserRole implements Serializable {
    /**
     *Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -3270490301182331694L;

    private Integer           userRoleId       = 0;

    /**
     * 获取 无意义自增ID。
     */
    public Integer getUserRoleId() {
        return this.userRoleId;
    }

    /**
     * 设置 无意义自增ID。
     *
     * @param value 属性值
     */
    public void setUserRoleId(Integer value) {
        this.userRoleId = value;
    }

    private Integer userId;

    public Integer getUserId() {
        return this.userId;
    }

    public void setUserId(Integer value) {
        this.userId = value;
    }

    private Integer roleId = 0;

    /**
     * 获取 角色ID。
     */
    public Integer getRoleId() {
        return this.roleId;
    }

    /**
     * 设置 角色ID。
     *
     * @param value 属性值
     */
    public void setRoleId(Integer value) {
        this.roleId = value;
    }

    private Date startTime = null;

    /**
     * 获取 开始时间。
     */
    public Date getStartTime() {
        return this.startTime;
    }

    /**
     * 设置 开始时间。
     *
     * @param value 属性值
     */
    public void setStartTime(Date value) {
        this.startTime = value;
    }

    private Date endTime = null;

    /**
     * 获取 结束时间。
     */
    public Date getEndTime() {
        return this.endTime;
    }

    /**
     * 设置 结束时间。
     *
     * @param value 属性值
     */
    public void setEndTime(Date value) {
        this.endTime = value;
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