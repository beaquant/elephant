package com.djt.cbs.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 团购城市表。
 *
 * <p>Table: <strong>group_city</strong>
 * <p><table class="er-mapping" cellspacing=0 cellpadding=0 style="border:solid 1 #666;padding:3px;">
 *   <tr style="background-color:#ddd;Text-align:Left;">
 *     <th nowrap>属性名</th><th nowrap>属性类型</th><th nowrap>字段名</th><th nowrap>字段类型</th><th nowrap>说明</th>
 *   </tr>
 *   <tr><td>cityCode</td><td>{@link String}</td><td>city_code</td><td>varchar</td><td>城市编码</td></tr>
 *   <tr><td>cityName</td><td>{@link String}</td><td>city_name</td><td>varchar</td><td>城市名称</td></tr>
 *   <tr><td>status</td><td>{@link Integer}</td><td>status</td><td>int</td><td>状态<br />-9：删除<br />0：停用<br />1：启用</td></tr>
 *   <tr><td>createTime</td><td>{@link Date}</td><td>create_time</td><td>datetime</td><td>创建时间</td></tr>
 *   <tr><td>createUser</td><td>{@link String}</td><td>create_user</td><td>varchar</td><td>创建人</td></tr>
 *   <tr><td>note</td><td>{@link String}</td><td>note</td><td>varchar</td><td>备注</td></tr>
 *   <tr><td>changeTime</td><td>{@link Date}</td><td>change_time</td><td>datetime</td><td>最后修改时间</td></tr>
 *   <tr><td>changeUser</td><td>{@link String}</td><td>change_user</td><td>varchar</td><td>最后修改人</td></tr>
 * </table>
 *
 * @author 周智勇
 * @date 2015-3-25
 * @email 36316500@qq.com
 */
public class GroupCity implements Serializable {
    /** Comment for <code>serialVersionUID</code> */
    private static final long   serialVersionUID = 7193135385927474570L;

    /**
     * 状态 - 删除
     */
    public static final Integer STATUS_DELETED   = -9;
    /**
     * 状态 - 停用
     */
    public static final Integer STATUS_STOP      = 0;
    /**
     * 状态 - 启用
     */
    public static final Integer STATUS_VALID     = 1;

    private String              cityCode;

    /**
     * 获取 城市编码。
     */
    public String getCityCode() {
        return this.cityCode;
    }

    /**
     * 设置 城市编码。
     *
     * @param value 属性值
     */
    public void setCityCode(String value) {
        this.cityCode = value;
    }

    private String cityName;

    /**
     * 获取 城市名称。
     */
    public String getCityName() {
        return this.cityName;
    }

    /**
     * 设置 城市名称。
     *
     * @param value 属性值
     */
    public void setCityName(String value) {
        this.cityName = value;
    }

    private Integer status;

    /**
     * 获取 状态。
     *
     * <p>
     * -9：删除<br />
     * 0：停用<br />
     * 1：启用
     */
    public Integer getStatus() {
        return this.status;
    }

    /**
     * 设置 状态。
     *
     * <p>
     * -9：删除<br />
     * 0：停用<br />
     * 1：启用
     *
     * @param value 属性值
     */
    public void setStatus(Integer value) {
        this.status = value;
    }

    private Date createTime;

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

    private String createUser;

    /**
     * 获取 创建人。
     */
    public String getCreateUser() {
        return this.createUser;
    }

    /**
     * 设置 创建人。
     *
     * @param value 属性值
     */
    public void setCreateUser(String value) {
        this.createUser = value;
    }

    private String note;

    /**
     * 获取 备注。
     */
    public String getNote() {
        return this.note;
    }

    /**
     * 设置 备注。
     *
     * @param value 属性值
     */
    public void setNote(String value) {
        this.note = value;
    }

    private Date changeTime;

    /**
     * 获取 最后修改时间。
     */
    public Date getChangeTime() {
        return this.changeTime;
    }

    /**
     * 设置 最后修改时间。
     *
     * @param value 属性值
     */
    public void setChangeTime(Date value) {
        this.changeTime = value;
    }

    private String changeUser;

    /**
     * 获取 最后修改人。
     */
    public String getChangeUser() {
        return this.changeUser;
    }

    /**
     * 设置 最后修改人。
     *
     * @param value 属性值
     */
    public void setChangeUser(String value) {
        this.changeUser = value;
    }

}