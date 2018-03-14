package com.djt.cbs.system.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>Table: <strong>sys_permission</strong>
 * <p><table class="er-mapping" cellspacing=0 cellpadding=0 style="border:solid 1 #666;padding:3px;">
 *   <tr style="background-color:#ddd;Text-align:Left;">
 *     <th nowrap>属性名</th><th nowrap>属性类型</th><th nowrap>字段名</th><th nowrap>字段类型</th><th nowrap>说明</th>
 *   </tr>
 *   <tr><td>permId</td><td>{@link Integer}</td><td>perm_id</td><td>int</td><td>&nbsp;</td></tr>
 *   <tr><td>ownerId</td><td>{@link Integer}</td><td>owner_id</td><td>int</td><td>权限所有者ID。</td></tr>
 *   <tr><td>ownerType</td><td>{@link Integer}</td><td>owner_type</td><td>tinyint</td><td>权限所有者类型。<br />1：用户；<br />2：角色；</td></tr>
 *   <tr><td>resId</td><td>{@link Integer}</td><td>res_id</td><td>int</td><td>权限资源ID。</td></tr>
 *   <tr><td>resType</td><td>{@link Integer}</td><td>res_type</td><td>varchar</td><td>权限资源类型。<br />1：操作；<br />2：功能菜单；</td></tr>
 *   <tr><td>startTime</td><td>{@link Date}</td><td>start_time</td><td>datetime</td><td>开始时间</td></tr>
 *   <tr><td>endTime</td><td>{@link Date}</td><td>end_time</td><td>datetime</td><td>结束时间</td></tr>
 *   <tr><td>updateUser</td><td>{@link String}</td><td>update_user</td><td>varchar</td><td>最后更新人。</td></tr>
 *   <tr><td>updateTime</td><td>{@link Date}</td><td>update_time</td><td>timestamp/date</td><td>最后更新时间。</td></tr>
 * </table>
 *
 */
public class SysPermission implements Serializable {
    /**
     *Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 8740373662191230996L;

    /**
     * 权限所有者为系统角色。
     */
    public final static int   OWNER_ROLE       = 2;
    /**
     * 权限所有者为系统用户。
     */
    public final static int   OWNER_USER       = 1;
    /**
     * 权限资源为系统操作。
     */
    public final static int   RESOURCE_ACTION  = 1;
    /**
     * 权限资源为系统菜单。
     */
    public final static int   RESOURCE_MENU    = 2;

    private Integer           permId;

    public Integer getPermId() {
        return this.permId;
    }

    public void setPermId(Integer value) {
        this.permId = value;
    }

    private Integer ownerId = 0;

    /**
     * 获取 权限所有者ID。
     */
    public Integer getOwnerId() {
        return this.ownerId;
    }

    /**
     * 设置 权限所有者ID。
     *
     * @param value 属性值
     */
    public void setOwnerId(Integer value) {
        this.ownerId = value;
    }

    private Integer ownerType = 0;

    /**
     * 获取 权限所有者类型。
     *
     * <p>
     * 1：用户；<br />
     * 2：角色；
     */
    public Integer getOwnerType() {
        return this.ownerType;
    }

    /**
     * 设置 权限所有者类型。
     *
     * <p>
     * 1：用户；<br />
     * 2：角色；
     *
     * @param value 属性值
     */
    public void setOwnerType(Integer value) {
        this.ownerType = value;
    }

    private Integer resId = 0;

    /**
     * 获取 权限资源ID。
     */
    public Integer getResId() {
        return this.resId;
    }

    /**
     * 设置 权限资源ID。
     *
     * @param value 属性值
     */
    public void setResId(Integer value) {
        this.resId = value;
    }

    private Integer resType;

    /**
     * 获取 权限资源类型。
     *
     * <p>
     * 1：操作；<br />
     * 2：功能菜单；
     */
    public Integer getResType() {
        return this.resType;
    }

    /**
     * 设置 权限资源类型。
     *
     * <p>
     * 1：操作；<br />
     * 2：功能菜单；
     *
     * @param value 属性值
     */
    public void setResType(Integer value) {
        this.resType = value;
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