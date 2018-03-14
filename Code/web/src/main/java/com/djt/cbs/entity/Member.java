package com.djt.cbs.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 会员表。
 *
 * <p>Table: <strong>member</strong>
 * <p><table class="er-mapping" cellspacing=0 cellpadding=0 style="border:solid 1 #666;padding:3px;">
 *   <tr style="background-color:#ddd;Text-align:Left;">
 *     <th nowrap>属性名</th><th nowrap>属性类型</th><th nowrap>字段名</th><th nowrap>字段类型</th><th nowrap>说明</th>
 *   </tr>
 *   <tr><td>memberId</td><td>{@link Integer}</td><td>member_id</td><td>int</td><td>会员ID</td></tr>
 *   <tr><td>mobile</td><td>{@link String}</td><td>mobile</td><td>varchar</td><td>手机号</td></tr>
 *   <tr><td>email</td><td>{@link String}</td><td>email</td><td>varchar</td><td>邮箱</td></tr>
 *   <tr><td>qQ</td><td>{@link String}</td><td>qq</td><td>varchar</td><td>QQ</td></tr>
 *   <tr><td>weChat</td><td>{@link String}</td><td>we_chat</td><td>varchar</td><td>微信号</td></tr>
 *   <tr><td>weChatOpenId</td><td>{@link String}</td><td>we_chat_open_id</td><td>varchar</td><td>微信OpenId</td></tr>
 *   <tr><td>name</td><td>{@link String}</td><td>name</td><td>varchar</td><td>姓名</td></tr>
 *   <tr><td>gender</td><td>{@link Integer}</td><td>gender</td><td>int</td><td>性别<br />0：未知<br />1：男<br />2：女</td></tr>
 *   <tr><td>cityCode</td><td>{@link String}</td><td>city_code</td><td>varchar</td><td>城市编码</td></tr>
 *   <tr><td>cityName</td><td>{@link String}</td><td>city_name</td><td>varchar</td><td>城市</td></tr>
 *   <tr><td>source</td><td>{@link String}</td><td>source</td><td>varchar</td><td>来源渠道<br />SC：商城<br />M_SC：移动商城<br />WX：微信服务号<br />HDXC：活动现场</td></tr>
 *   <tr><td>registedTime</td><td>{@link Date}</td><td>registed_time</td><td>datetime</td><td>注册时间</td></tr>
 *   <tr><td>monthCount</td><td>{@link Integer}</td><td>month_count</td><td>int</td><td>最近一个月参团次数</td></tr>
 *   <tr><td>yearCount</td><td>{@link Integer}</td><td>year_count</td><td>int</td><td>最近一年参团次数</td></tr>
 *   <tr><td>totalCount</td><td>{@link Integer}</td><td>total_count</td><td>int</td><td>总共参团次数</td></tr>
 *   <tr><td>changeTime</td><td>{@link Date}</td><td>change_time</td><td>datetime</td><td>最后修改时间</td></tr>
 *   <tr><td>neighborhood</td><td>{@link String}</td><td>neighborhood</td><td>varchar</td><td>所在小区</td></tr>
 *   <tr><td>inviterId</td><td>{@link Integer}</td><td>inviter_id</td><td>int</td><td>邀请人Id</td></tr>
 *   <tr><td>isPublic</td><td>{@link Integer}</td><td>is_public</td><td>int</td><td>是否公共资源</td></tr>
 * </table>
 *
 * @author 周智勇
 * @date 2015-3-26
 * @email 36316500@qq.com
 */
public class Member implements Serializable {
    /** Comment for <code>serialVersionUID</code> */
    private static final long serialVersionUID = -7767820106335345431L;

    private Integer           memberId;

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

    private String mobile;

    /**
     * 获取 手机号。
     */
    public String getMobile() {
        return this.mobile;
    }

    /**
     * 设置 手机号。
     *
     * @param value 属性值
     */
    public void setMobile(String value) {
        this.mobile = value;
    }

    private String email;

    /**
     * 获取 邮箱。
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * 设置 邮箱。
     *
     * @param value 属性值
     */
    public void setEmail(String value) {
        this.email = value;
    }

    private String qq;

    /**
     * 获取 QQ。
     */
    public String getQq() {
        return this.qq;
    }

    /**
     * 设置 QQ。
     *
     * @param value 属性值
     */
    public void setQq(String value) {
        this.qq = value;
    }

    private String weChat;

    /**
     * 获取 微信号。
     */
    public String getWeChat() {
        return this.weChat;
    }

    /**
     * 设置 微信号。
     *
     * @param value 属性值
     */
    public void setWeChat(String value) {
        this.weChat = value;
    }

    private String weChatOpenId;

    /**
     * 获取 微信OpenId。
     */
    public String getWeChatOpenId() {
        return this.weChatOpenId;
    }

    /**
     * 设置 微信OpenId。
     *
     * @param value 属性值
     */
    public void setWeChatOpenId(String value) {
        this.weChatOpenId = value;
    }

    private String name;

    /**
     * 获取 姓名。
     */
    public String getName() {
        return this.name;
    }

    /**
     * 设置 姓名。
     *
     * @param value 属性值
     */
    public void setName(String value) {
        this.name = value;
    }

    private Integer gender = 0;

    /**
     * 获取 性别。
     *
     * <p>
     * 0：未知<br />
     * 1：男<br />
     * 2：女
     */
    public Integer getGender() {
        return this.gender;
    }

    /**
     * 设置 性别。
     *
     * <p>
     * 0：未知<br />
     * 1：男<br />
     * 2：女
     *
     * @param value 属性值
     */
    public void setGender(Integer value) {
        this.gender = value;
    }

    private String cityCode;

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

    private String cityName = "武汉";

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

    private String source;

    /**
     * 获取 来源渠道。
     *
     * <p>
     * SC：商城<br />
     * M_SC：移动商城<br />
     * WX：微信服务号<br />
     * HDXC：活动现场
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
     * HDXC：活动现场
     *
     * @param value 属性值
     */
    public void setSource(String value) {
        this.source = value;
    }

    private Date registedTime;

    /**
     * 获取 注册时间。
     */
    public Date getRegistedTime() {
        return this.registedTime;
    }

    /**
     * 设置 注册时间。
     *
     * @param value 属性值
     */
    public void setRegistedTime(Date value) {
        this.registedTime = value;
    }

    private Integer monthCount = 0;

    /**
     * 获取 最近一个月参团次数。
     */
    public Integer getMonthCount() {
        return this.monthCount;
    }

    /**
     * 设置 最近一个月参团次数。
     *
     * @param value 属性值
     */
    public void setMonthCount(Integer value) {
        this.monthCount = value;
    }

    private Integer yearCount = 0;

    /**
     * 获取 最近一年参团次数。
     */
    public Integer getYearCount() {
        return this.yearCount;
    }

    /**
     * 设置 最近一年参团次数。
     *
     * @param value 属性值
     */
    public void setYearCount(Integer value) {
        this.yearCount = value;
    }

    private Integer totalCount = 0;

    /**
     * 获取 总共参团次数。
     */
    public Integer getTotalCount() {
        return this.totalCount;
    }

    /**
     * 设置 总共参团次数。
     *
     * @param value 属性值
     */
    public void setTotalCount(Integer value) {
        this.totalCount = value;
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

    private String neighborhood;

    /**
     * 获取 所在小区。
     */
    public String getNeighborhood() {
        return this.neighborhood;
    }

    /**
     * 设置 所在小区。
     *
     * @param value 属性值
     */
    public void setNeighborhood(String value) {
        this.neighborhood = value;
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