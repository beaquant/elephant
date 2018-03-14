package com.djt.cbs.system.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 角色。
 *
 * <p>Table: <strong>sys_role</strong>
 * <p><table class="er-mapping" cellspacing=0 cellpadding=0 style="border:solid 1 #666;padding:3px;">
 *   <tr style="background-color:#ddd;Text-align:Left;">
 *     <th nowrap>属性名</th><th nowrap>属性类型</th><th nowrap>字段名</th><th nowrap>字段类型</th><th nowrap>说明</th>
 *   </tr>
 *   <tr><td>roleId</td><td>{@link Integer}</td><td>role_id</td><td>int</td><td>角色ID。</td></tr>
 *   <tr><td>roleName</td><td>{@link String}</td><td>role_name</td><td>varchar</td><td>角色名称。</td></tr>
 *   <tr><td>status</td><td>{@link Integer}</td><td>status</td><td>int</td><td>状态。<br />-1逻辑删除，0禁用，1启用</td></tr>
 *   <tr><td>remark</td><td>{@link String}</td><td>remark</td><td>varchar</td><td>备注。</td></tr>
 *   <tr><td>createUser</td><td>{@link String}</td><td>create_user</td><td>varchar</td><td>创建者。</td></tr>
 *   <tr><td>createTime</td><td>{@link Date}</td><td>create_time</td><td>datetime</td><td>创建时间。</td></tr>
 *   <tr><td>updateUser</td><td>{@link String}</td><td>update_user</td><td>varchar</td><td>最后更新人。</td></tr>
 *   <tr><td>updateTime</td><td>{@link Date}</td><td>update_time</td><td>timestamp/date</td><td>最后更新时间。</td></tr>
 * </table>
 *
 */
public class SysRole implements Serializable {
    /**
     *Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -7771206507293783692L;

    private Integer           roleId           = 0;

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

    private String roleName = "";

    /**
     * 获取 角色名称。
     */
    public String getRoleName() {
        return this.roleName;
    }

    /**
     * 设置 角色名称。
     *
     * @param value 属性值
     */
    public void setRoleName(String value) {
        this.roleName = value;
    }

    private Integer status = 0;

    /**
     * 获取 状态。
     *
     * <p>
     * -1逻辑删除，0禁用，1启用
     */
    public Integer getStatus() {
        return this.status;
    }

    /**
     * 设置 状态。
     *
     * <p>
     * -1逻辑删除，0禁用，1启用
     *
     * @param value 属性值
     */
    public void setStatus(Integer value) {
        this.status = value;
    }

    private String remark = "";

    /**
     * 获取 备注。
     */
    public String getRemark() {
        return this.remark;
    }

    /**
     * 设置 备注。
     *
     * @param value 属性值
     */
    public void setRemark(String value) {
        this.remark = value;
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