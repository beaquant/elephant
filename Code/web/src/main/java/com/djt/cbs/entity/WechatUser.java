package com.djt.cbs.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 微信用户。
 *
 * <p>Table: <strong>wechat_user</strong>
 * <p><table class="er-mapping" cellspacing=0 cellpadding=0 style="border:solid 1 #666;padding:3px;">
 *   <tr style="background-color:#ddd;Text-align:Left;">
 *     <th nowrap>属性名</th><th nowrap>属性类型</th><th nowrap>字段名</th><th nowrap>字段类型</th><th nowrap>说明</th>
 *   </tr>
 *   <tr><td>userId</td><td>{@link Integer}</td><td>user_id</td><td>int</td><td>用户ID<br />从800000001开始</td></tr>
 *   <tr><td>openId</td><td>{@link String}</td><td>open_id</td><td>varchar</td><td>OpenId</td></tr>
 *   <tr><td>nickName</td><td>{@link String}</td><td>nick_name</td><td>varchar</td><td>用户昵称</td></tr>
 *   <tr><td>headImgUrl</td><td>{@link String}</td><td>head_img_url</td><td>varchar</td><td>微信头像</td></tr>
 *   <tr><td>createTime</td><td>{@link Date}</td><td>create_time</td><td>datetime</td><td>创建时间</td></tr>
 *   <tr><td>subscribeTime</td><td>{@link Date}</td><td>subscribe_time</td><td>datetime</td><td>关注时间</td></tr>
 *   <tr><td>unsubscribeTime</td><td>{@link Date}</td><td>unsubscribe_time</td><td>datetime</td><td>取消关注时间</td></tr>
 *   <tr><td>isSubscribe</td><td>{@link Object}</td><td>is_subscribe</td><td>bit</td><td>是否关注<br />1：是<br />0：否</td></tr>
 *   <tr><td>invitedAmount</td><td>{@link Integer}</td><td>invited_amount</td><td>int</td><td>邀请人数<br />邀请好友关注增加<br />好友取消关注减少</td></tr>
 *   <tr><td>inviteTicket</td><td>{@link String}</td><td>invite_ticket</td><td>varchar</td><td>推广二维码</td></tr>
 *   <tr><td>inviteImage</td><td>{@link String}</td><td>invite_image</td><td>varchar</td><td>推广图片</td></tr>
 *   <tr><td>ticketCreateTime</td><td>{@link Date}</td><td>ticket_create_time</td><td>datetime</td><td>二维码生成时间</td></tr>
 *   <tr><td>mediaId</td><td>{@link String}</td><td>media_id</td><td>varchar</td><td>微信mediaId（推广图片的）</td></tr>
 *   <tr><td>inviteImageUploadTime</td><td>{@link Date}</td><td>invite_image_upload_time</td><td>datetime</td><td>推广图片上传时间</td></tr>
 * </table>
 *
 * @author 周智勇
 * @date 2015-5-22
 * @email 36316500@qq.com
 */
public class WechatUser implements Serializable {
    /** Comment for <code>serialVersionUID</code> */
    private static final long serialVersionUID = 2244777246601257779L;

    private Integer           userId;

    /**
     * 获取 用户ID。
     *
     * <p>
     * 从800000001开始
     */
    public Integer getUserId() {
        return this.userId;
    }

    /**
     * 设置 用户ID。
     *
     * <p>
     * 从800000001开始
     *
     * @param value 属性值
     */
    public void setUserId(Integer value) {
        this.userId = value;
    }

    private String openId;

    /**
     * 获取 OpenId。
     */
    public String getOpenId() {
        return this.openId;
    }

    /**
     * 设置 OpenId。
     *
     * @param value 属性值
     */
    public void setOpenId(String value) {
        this.openId = value;
    }

    private String nickName;

    /**
     * 获取 昵称
     * @return
     */
    public String getNickName() {
        return nickName;
    }

    /**
     * 设置 昵称
     * @param nickName
     */
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    private String headImgUrl;

    /**
     * 获取 微信头像。
     */
    public String getHeadImgUrl() {
        return this.headImgUrl;
    }

    /**
     * 设置 微信头像。
     *
     * @param value 属性值
     */
    public void setHeadImgUrl(String value) {
        this.headImgUrl = value;
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

    private Date subscribeTime;

    /**
     * 获取 关注时间。
     */
    public Date getSubscribeTime() {
        return this.subscribeTime;
    }

    /**
     * 设置 关注时间。
     *
     * @param value 属性值
     */
    public void setSubscribeTime(Date value) {
        this.subscribeTime = value;
    }

    private Date unsubscribeTime;

    /**
     * 获取 取消关注时间。
     */
    public Date getUnsubscribeTime() {
        return this.unsubscribeTime;
    }

    /**
     * 设置 取消关注时间。
     *
     * @param value 属性值
     */
    public void setUnsubscribeTime(Date value) {
        this.unsubscribeTime = value;
    }

    private Object isSubscribe;

    /**
     * 获取 是否关注。
     *
     * <p>
     * 1：是<br />
     * 0：否
     */
    public Object getIsSubscribe() {
        return this.isSubscribe;
    }

    /**
     * 设置 是否关注。
     *
     * <p>
     * 1：是<br />
     * 0：否
     *
     * @param value 属性值
     */
    public void setIsSubscribe(Object value) {
        this.isSubscribe = value;
    }

    private Integer invitedAmount;

    /**
     * 获取 邀请人数。
     *
     * <p>
     * 邀请好友关注增加<br />
     * 好友取消关注减少
     */
    public Integer getInvitedAmount() {
        return this.invitedAmount;
    }

    /**
     * 设置 邀请人数。
     *
     * <p>
     * 邀请好友关注增加<br />
     * 好友取消关注减少
     *
     * @param value 属性值
     */
    public void setInvitedAmount(Integer value) {
        this.invitedAmount = value;
    }

    private String inviteTicket;

    /**
     * 获取 推广二维码。
     */
    public String getInviteTicket() {
        return this.inviteTicket;
    }

    /**
     * 设置 推广二维码。
     *
     * @param value 属性值
     */
    public void setInviteTicket(String value) {
        this.inviteTicket = value;
    }

    private String inviteImage;

    /**
     * 获取 推广图片。
     */
    public String getInviteImage() {
        return this.inviteImage;
    }

    /**
     * 设置 推广图片。
     *
     * @param value 属性值
     */
    public void setInviteImage(String value) {
        this.inviteImage = value;
    }

    private Date ticketCreateTime;

    /**
     * 获取 二维码生成时间
     * @return
     */
    public Date getTicketCreateTime() {
        return ticketCreateTime;
    }

    /**
     * 设置 二维码生成时间
     * @param ticketCreateTime
     */
    public void setTicketCreateTime(Date ticketCreateTime) {
        this.ticketCreateTime = ticketCreateTime;
    }

    private String mediaId;

    /**
     * 获取 微信mediaId（推广图片的）。
     */
    public String getMediaId() {
        return this.mediaId;
    }

    /**
     * 设置 微信mediaId（推广图片的）。
     *
     * @param value 属性值
     */
    public void setMediaId(String value) {
        this.mediaId = value;
    }

    private Date inviteImageUploadTime;

    /**
     * 获取 推广图片上传时间。
     */
    public Date getInviteImageUploadTime() {
        return this.inviteImageUploadTime;
    }

    /**
     * 设置 推广图片上传时间。
     *
     * @param value 属性值
     */
    public void setInviteImageUploadTime(Date value) {
        this.inviteImageUploadTime = value;
    }

}