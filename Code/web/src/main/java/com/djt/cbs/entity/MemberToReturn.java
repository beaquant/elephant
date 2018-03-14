package com.djt.cbs.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 回访队列表。
 *
 * <p>Table: <strong>member_to_return</strong>
 * <p><table class="er-mapping" cellspacing=0 cellpadding=0 style="border:solid 1 #666;padding:3px;">
 *   <tr style="background-color:#ddd;Text-align:Left;">
 *     <th nowrap>属性名</th><th nowrap>属性类型</th><th nowrap>字段名</th><th nowrap>字段类型</th><th nowrap>说明</th>
 *   </tr>
 *   <tr><td>id</td><td>{@link Integer}</td><td>id</td><td>int</td><td>流水ID</td></tr>
 *   <tr><td>foreignKey</td><td>{@link String}</td><td>foreign_key</td><td>varchar</td><td>外部关键ID<br />如：<br />报名ID<br />签到ID<br />订单ID</td></tr>
 *   <tr><td>mobile</td><td>{@link String}</td><td>mobile</td><td>varchar</td><td>手机号</td></tr>
 *   <tr><td>memberId</td><td>{@link Integer}</td><td>member_id</td><td>int</td><td>会员ID</td></tr>
 *   <tr><td>groupType</td><td>{@link String}</td><td>group_type</td><td>char</td><td>团购类型<br />HOU：房产<br />CAR：汽车<br />BME：建材<br />HEA：家电</td></tr>
 *   <tr><td>createTime</td><td>{@link Date}</td><td>create_time</td><td>datetime</td><td>创建时间</td></tr>
 *   <tr><td>returnType</td><td>{@link Integer}</td><td>return_type</td><td>int</td><td>回访类型<br />1：新报名<br />2：首次签到<br />3：首次下单<br />4：装修完工</td></tr>
 *   <tr><td>returnTime</td><td>{@link Date}</td><td>return_time</td><td>datetime</td><td>回访时间</td></tr>
 *   <tr><td>returnUser</td><td>{@link String}</td><td>return_user</td><td>varchar</td><td>回访人</td></tr>
 *   <tr><td>status</td><td>{@link Integer}</td><td>status</td><td>int</td><td>状态<br />0：待处理<br />100：已处理</td></tr>
 *   <tr><td>note</td><td>{@link String}</td><td>note</td><td>varchar</td><td>备注</td></tr>
 * </table>
 *
 * @author 周智勇
 * @date 2015-4-8
 * @email 36316500@qq.com
 */
public class MemberToReturn implements Serializable {
    /** Comment for <code>serialVersionUID</code> */
    private static final long   serialVersionUID              = 5422249665190194579L;

    /**
     * 回访类型 - 新报名
     */
    public static final Integer RETURN_TYPE_NEW_ENTERED       = 1;
    /**
     * 回访类型 - 首次签到
     */
    public static final Integer RETURN_TYPE_NEW_FIRST_SIGNUP  = 2;
    /**
     * 回访类型 - 首次下单
     */
    public static final Integer RETURN_TYPE_NEW_FIRST_ORDERED = 3;
    /**
     * 回访类型 - 装修完工
     */
    public static final Integer RETURN_TYPE_DECORATED         = 4;

    /**
     * 状态 - 待处理
     */
    public static final Integer STATUS_TO_PROCESS             = 0;
    /**
     * 状态 - 手机号无效
     */
    public static final Integer STATUS_MOBILE_INVALID         = 10;
    /**
     * 状态 - 已处理
     */
    public static final Integer STATUS_DONE                   = 100;

    private Integer             id;

    /**
     * 获取 流水ID。
     */
    public Integer getId() {
        return this.id;
    }

    /**
     * 设置 流水ID。
     *
     * @param value 属性值
     */
    public void setId(Integer value) {
        this.id = value;
    }

    private String foreignKey;

    /**
     * 获取 外部关键ID。
     *
     * <p>
     * 如：<br />
     * 报名ID<br />
     * 签到ID<br />
     * 订单ID
     */
    public String getForeignKey() {
        return this.foreignKey;
    }

    /**
     * 设置 外部关键ID。
     *
     * <p>
     * 如：<br />
     * 报名ID<br />
     * 签到ID<br />
     * 订单ID
     *
     * @param value 属性值
     */
    public void setForeignKey(String value) {
        this.foreignKey = value;
    }

    private String mobile;

    /**
     * 设置 手机号
     * @return
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * 获取 手机号
     * @param mobile
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    private Integer memberId;

    /**
     * 获取 会员ID。
     */
    public Integer getMemberId() {
        return this.memberId;
    }

    /**
     * 设置 会员ID。
     *
     * @param value 属性值
     */
    public void setMemberId(Integer value) {
        this.memberId = value;
    }

    private String groupType;

    /**
     * 获取 团购类型。
     *
     * <p>
     * HOU：房产<br />
     * CAR：汽车<br />
     * BME：建材<br />
     * HEA：家电
     */
    public String getGroupType() {
        return this.groupType;
    }

    /**
     * 设置 团购类型。
     *
     * <p>
     * HOU：房产<br />
     * CAR：汽车<br />
     * BME：建材<br />
     * HEA：家电
     *
     * @param value 属性值
     */
    public void setGroupType(String value) {
        this.groupType = value;
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

    private Integer returnType;

    /**
     * 获取 回访类型。
     *
     * <p>
     * 1：新报名<br />
     * 2：首次签到<br />
     * 3：首次下单<br />
     * 4：装修完工
     */
    public Integer getReturnType() {
        return this.returnType;
    }

    /**
     * 设置 回访类型。
     *
     * <p>
     * 1：新报名<br />
     * 2：首次签到<br />
     * 3：首次下单<br />
     * 4：装修完工
     *
     * @param value 属性值
     */
    public void setReturnType(Integer value) {
        this.returnType = value;
    }

    private Date returnTime;

    /**
     * 获取 回访时间。
     */
    public Date getReturnTime() {
        return this.returnTime;
    }

    /**
     * 设置 回访时间。
     *
     * @param value 属性值
     */
    public void setReturnTime(Date value) {
        this.returnTime = value;
    }

    private String returnUser;

    /**
     * 获取 回访人。
     */
    public String getReturnUser() {
        return this.returnUser;
    }

    /**
     * 设置 回访人。
     *
     * @param value 属性值
     */
    public void setReturnUser(String value) {
        this.returnUser = value;
    }

    private Integer status;

    /**
     * 获取 状态。
     *
     * <p>
     * 0：待处理<br />
     * 10：手机号无效<br />
     * 100：已处理
     */
    public Integer getStatus() {
        return this.status;
    }

    /**
     * 设置 状态。
     *
     * <p>
     * 0：待处理<br />
     * 10：手机号无效<br />
     * 100：已处理
     *
     * @param value 属性值
     */
    public void setStatus(Integer value) {
        this.status = value;
    }

    private String note;

    /**
     * 获取 备注
     * @return
     */
    public String getNote() {
        return note;
    }

    /**
     * 设置 备注
     * @param note
     */
    public void setNote(String note) {
        this.note = note;
    }

}