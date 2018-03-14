package com.djt.cbs.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 会员参团表。
 *
 * <p>Table: <strong>member_group</strong>
 * <p><table class="er-mapping" cellspacing=0 cellpadding=0 style="border:solid 1 #666;padding:3px;">
 *   <tr style="background-color:#ddd;Text-align:Left;">
 *     <th nowrap>属性名</th><th nowrap>属性类型</th><th nowrap>字段名</th><th nowrap>字段类型</th><th nowrap>说明</th>
 *   </tr>
 *   <tr><td>groupOrderId</td><td>{@link Integer}</td><td>group_order_id</td><td>int</td><td>团购订单Id</td></tr>
 *   <tr><td>gpId</td><td>{@link Integer}</td><td>gp_id</td><td>int</td><td>&nbsp;</td></tr>
 *   <tr><td>memberId</td><td>{@link Integer}</td><td>member_id</td><td>int</td><td>会员ID</td></tr>
 *   <tr><td>activityName</td><td>{@link String}</td><td>activity_name</td><td>varchar</td><td>活动名称</td></tr>
 *   <tr><td>groupType</td><td>{@link String}</td><td>group_type</td><td>char</td><td>团购类型<br />HOU：房产<br />CAR：汽车<br />BME：建材<br />HEA：家电</td></tr>
 *   <tr><td>memberStatus</td><td>{@link Integer}</td><td>member_status</td><td>int</td><td>会员状态<br />0：已确认<br />20：已签到</td></tr>
 *   <tr><td>source</td><td>{@link String}</td><td>source</td><td>varchar</td><td>来源渠道<br />SC：商城<br />M_SC：移动商城<br />WX：微信服务号<br />HDXC：活动现场<br />DH：电话</td></tr>
 *   <tr><td>cityName</td><td>{@link String}</td><td>city_name</td><td>varchar</td><td>城市</td></tr>
 *   <tr><td>createTime</td><td>{@link Date}</td><td>create_time</td><td>datetime</td><td>创建时间</td></tr>
 *   <tr><td>confirmTime</td><td>{@link Date}</td><td>confirm_time</td><td>datetime</td><td>确认时间</td></tr>
 *   <tr><td>confirmUser</td><td>{@link String}</td><td>confirm_user</td><td>varchar</td><td>确认人</td></tr>
 *   <tr><td>signupTime</td><td>{@link Date}</td><td>signup_time</td><td>datetime</td><td>签到时间</td></tr>
 *   <tr><td>signupUser</td><td>{@link String}</td><td>signup_user</td><td>varchar</td><td>签到人</td></tr>
 *   <tr><td>changeTime</td><td>{@link Date}</td><td>change_time</td><td>datetime</td><td>最后修改时间</td></tr>
 *   <tr><td>mobile</td><td>{@link String}</td><td>mobile</td><td>varchar</td><td>签到手机号</td></tr>
 *   <tr><td>inviterId</td><td>{@link Integer}</td><td>inviter_id</td><td>int</td><td>邀请人Id</td></tr>
 *   <tr><td>isPublic</td><td>{@link Integer}</td><td>is_public</td><td>int</td><td>是否公共资源</td></tr>
 * </table>
 *
 * @author 周智勇
 * @date 2015-3-26
 * @email 36316500@qq.com
 */
public class MemberGroup implements Serializable {
    /** Comment for <code>serialVersionUID</code> */
    private static final long   serialVersionUID        = -7179794822034425212L;

    /**
     * 会员状态 -  已确认
     */
    public static final Integer MEMBER_STATUS_CONFIRMED = 0;
    /**
     * 会员状态 -  已签到
     */
    public static final Integer MEMBER_STATUS_SIGNUP    = 20;

    private Integer             groupOrderId;

    /**
     * 获取 团购订单Id。
     */
    public Integer getGroupOrderId() {
        return this.groupOrderId;
    }

    /**
     * 设置 团购订单Id。
     *
     * @param value 属性值
     */
    public void setGroupOrderId(Integer value) {
        this.groupOrderId = value;
    }

    private Integer gpId;

    public Integer getGpId() {
        return this.gpId;
    }

    public void setGpId(Integer value) {
        this.gpId = value;
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

    private String activityName;

    /**
     * 获取 活动名称。
     */
    public String getActivityName() {
        return this.activityName;
    }

    /**
     * 设置 活动名称。
     *
     * @param value 属性值
     */
    public void setActivityName(String value) {
        this.activityName = value;
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

    private Integer memberStatus;

    /**
     * 获取 会员状态。
     *
     * <p>
     * 0：已确认<br />
     * 20：已签到
     */
    public Integer getMemberStatus() {
        return this.memberStatus;
    }

    /**
     * 设置 会员状态。
     *
     * <p>
     * 0：已确认<br />
     * 20：已签到
     *
     * @param value 属性值
     */
    public void setMemberStatus(Integer value) {
        this.memberStatus = value;
    }

    private String source;

    /**
     * 获取 来源渠道。
     *
     * <p>
     * SC：商城<br />
     * M_SC：移动商城<br />
     * WX：微信服务号<br />
     * HDXC：活动现场<br />
     * DH：电话
     */
    public String getSource() {
        return this.source;
    }

    /**
     * 设置 来源渠道。
     *
     * <p>
     * SC：商城<br />
     * M_SC：移动商城<br />
     * WX：微信服务号<br />
     * HDXC：活动现场<br />
     * DH：电话
     *
     * @param value 属性值
     */
    public void setSource(String value) {
        this.source = value;
    }

    private String cityName;

    /**
     * 获取 城市。
     */
    public String getCityName() {
        return this.cityName;
    }

    /**
     * 设置 城市。
     *
     * @param value 属性值
     */
    public void setCityName(String value) {
        this.cityName = value;
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

    private Date confirmTime;

    /**
     * 获取 确认时间。
     */
    public Date getConfirmTime() {
        return this.confirmTime;
    }

    /**
     * 设置 确认时间。
     *
     * @param value 属性值
     */
    public void setConfirmTime(Date value) {
        this.confirmTime = value;
    }

    private String confirmUser;

    /**
     * 获取 确认人。
     */
    public String getConfirmUser() {
        return this.confirmUser;
    }

    /**
     * 设置 确认人。
     *
     * @param value 属性值
     */
    public void setConfirmUser(String value) {
        this.confirmUser = value;
    }

    private Date signupTime;

    /**
     * 获取 签到时间。
     */
    public Date getSignupTime() {
        return this.signupTime;
    }

    /**
     * 设置 签到时间。
     *
     * @param value 属性值
     */
    public void setSignupTime(Date value) {
        this.signupTime = value;
    }

    private String signupUser;

    /**
     * 获取 签到人。
     */
    public String getSignupUser() {
        return this.signupUser;
    }

    /**
     * 设置 签到人。
     *
     * @param value 属性值
     */
    public void setSignupUser(String value) {
        this.signupUser = value;
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

    private String mobile;

    /**
     * 获取 签到手机号
     * @return
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * 设置 签到手机号
     * @param mobile
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    private Integer inviterId = 0;

    /**
     * 获取 邀请人Id
     * @return
     */
    public Integer getInviterId() {
        return inviterId;
    }

    /**
     * 设置 邀请人Id
     * @return
     */
    public void setInviterId(Integer inviterId) {
        this.inviterId = inviterId;
    }

    private Integer isPublic = 0;

    /**
     * 获取 是否公共资源
     * @return
     */
    public Integer getIsPublic() {
        return isPublic;
    }

    /**
     * 设置 是否公共资源
     * @param isPublic
     */
    public void setIsPublic(Integer isPublic) {
        this.isPublic = isPublic;
    }

}