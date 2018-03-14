package com.djt.cbs.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 会员回访表。
 *
 * <p>Table: <strong>member_return</strong>
 * <p><table class="er-mapping" cellspacing=0 cellpadding=0 style="border:solid 1 #666;padding:3px;">
 *   <tr style="background-color:#ddd;Text-align:Left;">
 *     <th nowrap>属性名</th><th nowrap>属性类型</th><th nowrap>字段名</th><th nowrap>字段类型</th><th nowrap>说明</th>
 *   </tr>
 *   <tr><td>id</td><td>{@link Integer}</td><td>id</td><td>int</td><td>流水ID</td></tr>
 *   <tr><td>memberKey</td><td>{@link String}</td><td>member_key</td><td>varchar</td><td>会员识别码<br />手机，或者QQ号，或者邮箱</td></tr>
 *   <tr><td>orderNo</td><td>{@link String}</td><td>order_no</td><td>varchar</td><td>订单编号</td></tr>
 *   <tr><td>foreignKey</td><td>{@link String}</td><td>foreign_key</td><td>varchar</td><td>外部关键ID<br />如：<br />报名ID<br />签到ID<br />订单ID</td></tr>
 *   <tr><td>memberId</td><td>{@link Integer}</td><td>member_id</td><td>int</td><td>会员ID</td></tr>
 *   <tr><td>orderId</td><td>{@link Integer}</td><td>order_id</td><td>int</td><td>订单ID</td></tr>
 *   <tr><td>groupType</td><td>{@link String}</td><td>group_type</td><td>char</td><td>团购类型<br />HOU：房产<br />CAR：汽车<br />BME：建材<br />HEA：家电</td></tr>
 *   <tr><td>returnTime</td><td>{@link Date}</td><td>return_time</td><td>datetime</td><td>回访时间</td></tr>
 *   <tr><td>returnType</td><td>{@link Integer}</td><td>return_type</td><td>int</td><td>回访类型<br />1：新报名<br />2：首次签到<br />3：首次下单<br />4：装修完工</td></tr>
 *   <tr><td>returnUser</td><td>{@link String}</td><td>return_user</td><td>varchar</td><td>回访人</td></tr>
 *   <tr><td>returnContent</td><td>{@link String}</td><td>return_content</td><td>varchar</td><td>回访内容</td></tr>
 * </table>
 *
 * @author 周智勇
 * @date 2015-4-1
 * @email 36316500@qq.com
 */
public class MemberReturn implements Serializable {
    /** Comment for <code>serialVersionUID</code> */
    private static final long   serialVersionUID              = 8418840250966620907L;

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

    private String memberKey;

    /**
     * 获取 会员识别码。
     *
     * <p>
     * 手机，或者QQ号，或者邮箱
     */
    public String getMemberKey() {
        return this.memberKey;
    }

    /**
     * 设置 会员识别码。
     *
     * <p>
     * 手机，或者QQ号，或者邮箱
     *
     * @param value 属性值
     */
    public void setMemberKey(String value) {
        this.memberKey = value;
    }

    private String orderNo;

    /**
     * 获取 订单编号。
     */
    public String getOrderNo() {
        return this.orderNo;
    }

    /**
     * 设置 订单编号。
     *
     * @param value 属性值
     */
    public void setOrderNo(String value) {
        this.orderNo = value;
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

    private Integer orderId;

    /**
     * 获取 订单ID。
     */
    public Integer getOrderId() {
        return this.orderId;
    }

    /**
     * 设置 订单ID。
     *
     * @param value 属性值
     */
    public void setOrderId(Integer value) {
        this.orderId = value;
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

    private String returnContent;

    /**
     * 获取 回访内容。
     */
    public String getReturnContent() {
        return this.returnContent;
    }

    /**
     * 设置 回访内容。
     *
     * @param value 属性值
     */
    public void setReturnContent(String value) {
        this.returnContent = value;
    }

}