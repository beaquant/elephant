package com.djt.cbs.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 邀请人表（团长表）。
 *
 * <p>Table: <strong>inviter</strong>
 * <p><table class="er-mapping" cellspacing=0 cellpadding=0 style="border:solid 1 #666;padding:3px;">
 *   <tr style="background-color:#ddd;Text-align:Left;">
 *     <th nowrap>属性名</th><th nowrap>属性类型</th><th nowrap>字段名</th><th nowrap>字段类型</th><th nowrap>说明</th>
 *   </tr>
 *   <tr><td>inviterId</td><td>{@link Integer}</td><td>inviter_id</td><td>int</td><td>邀请人ID</td></tr>
 *   <tr><td>name</td><td>{@link String}</td><td>name</td><td>varchar</td><td>姓名</td></tr>
 *   <tr><td>mobile</td><td>{@link String}</td><td>mobile</td><td>varchar</td><td>手机号</td></tr>
 *   <tr><td>phone</td><td>{@link String}</td><td>phone</td><td>varchar</td><td>电话号码</td></tr>
 *   <tr><td>qq</td><td>{@link String}</td><td>qq</td><td>varchar</td><td>QQ号</td></tr>
 *   <tr><td>weChat</td><td>{@link String}</td><td>we_chat</td><td>varchar</td><td>微信号</td></tr>
 *   <tr><td>status</td><td>{@link Integer}</td><td>status</td><td>int</td><td>状态：<br />-1：删除<br />0：冻结<br />1：活跃<br /></td></tr>
 *   <tr><td>note</td><td>{@link String}</td><td>note</td><td>varchar</td><td>备注</td></tr>
 *   <tr><td>cbsUserId</td><td>{@link Integer}</td><td>cbs_user_id</td><td>int</td><td>CBS系统UserId</td></tr>
 *   <tr><td>avatar</td><td>{@link String}</td><td>avatar</td><td>varchar</td><td>头像</td></tr>
 *   <tr><td>qrCode</td><td>{@link String}</td><td>qr_code</td><td>varchar</td><td>微信二维码</td></tr>
 *   <tr><td>createTime</td><td>{@link Date}</td><td>create_time</td><td>datetime</td><td>创建时间</td></tr>
 *   <tr><td>createUser</td><td>{@link String}</td><td>create_user</td><td>varchar</td><td>创建人</td></tr>
 *   <tr><td>updateTime</td><td>{@link Date}</td><td>update_time</td><td>datetime</td><td>修改时间</td></tr>
 *   <tr><td>updateUser</td><td>{@link String}</td><td>update_user</td><td>varchar</td><td>修改人</td></tr>
 *   <tr><td>groupType</td><td>{@link Integer}</td><td>group_type</td><td>int</td><td>群组：1：推广组<br />9：其他组</td></tr>
 *   <tr><td>inviteImage</td><td>{@link String}</td><td>invite_image</td><td>varchar</td><td>推广二维码图片，合成了个人头像的</td></tr>
 *   <tr><td>invitedAmount</td><td>{@link Integer}</td><td>invited_amount</td><td>int</td><td>推广二维码数</td></tr>
 * </table>
 *
 * @author 周智勇
 * @date 2015-4-27
 * @email 36316500@qq.com
 */
public class Inviter implements Serializable {
    /** Comment for <code>serialVersionUID</code> */
    private static final long serialVersionUID          = -2812346505764557197L;

    /**
     * 状态 - 删除
     */
    public static Integer     STATUS_DELETED            = -1;
    /**
     * 状态 - 冻结
     */
    public static Integer     STATUS_FROZEN             = 0;
    /**
     * 状态 - 活跃
     */
    public static Integer     STATUS_VALID              = 1;

    /**
     * 群组 - 推广组
     */
    public static Integer     GROUP_TYPE_POPULARIZATION = 1;
    /**
     * 群组 - 其他组
     */
    public static Integer     GROUP_TYPE_OTHER          = 9;

    private Integer           inviterId;

    public Integer getInviterId() {
        return this.inviterId;
    }

    public void setInviterId(Integer value) {
        this.inviterId = value;
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

    private String phone;

    /**
     * 获取 电话号码。
     */
    public String getPhone() {
        return this.phone;
    }

    /**
     * 设置 电话号码。
     *
     * @param value 属性值
     */
    public void setPhone(String value) {
        this.phone = value;
    }

    private String qq;

    /**
     * 获取 QQ号。
     */
    public String getQq() {
        return this.qq;
    }

    /**
     * 设置 QQ号。
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

    private Integer status;

    /**
     * 获取 状态：。
     *
     * <p>
     * -1：删除<br />
     * 0：冻结<br />
     * 1：在用<br />
     * 
     */
    public Integer getStatus() {
        return this.status;
    }

    /**
     * 设置 状态：。
     *
     * <p>
     * -1：删除<br />
     * 0：冻结<br />
     * 1：在用<br />
     * 
     *
     * @param value 属性值
     */
    public void setStatus(Integer value) {
        this.status = value;
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

    private Integer cbsUserId = 0;

    /**
     * 获取 CBS系统UserId。
     */
    public Integer getCbsUserId() {
        return this.cbsUserId;
    }

    /**
     * 设置 CBS系统UserId。
     *
     * @param value 属性值
     */
    public void setCbsUserId(Integer value) {
        this.cbsUserId = value;
    }

    private String avatar;

    /**
     * 获取 头像。
     */
    public String getAvatar() {
        return this.avatar;
    }

    /**
     * 设置 头像。
     *
     * @param value 属性值
     */
    public void setAvatar(String value) {
        this.avatar = value;
    }

    private String qrCode;

    /**
     * 获取 微信二维码。
     */
    public String getQrCode() {
        return this.qrCode;
    }

    /**
     * 设置 微信二维码。
     *
     * @param value 属性值
     */
    public void setQrCode(String value) {
        this.qrCode = value;
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

    private Date updateTime;

    /**
     * 获取 修改时间。
     */
    public Date getUpdateTime() {
        return this.updateTime;
    }

    /**
     * 设置 修改时间。
     *
     * @param value 属性值
     */
    public void setUpdateTime(Date value) {
        this.updateTime = value;
    }

    private String updateUser;

    /**
     * 获取 修改人。
     */
    public String getUpdateUser() {
        return this.updateUser;
    }

    /**
     * 设置 修改人。
     *
     * @param value 属性值
     */
    public void setUpdateUser(String value) {
        this.updateUser = value;
    }

    private Integer groupType;

    /**
     * 获取 群组
     * @return
     */
    public Integer getGroupType() {
        return groupType;
    }

    /**
     * 设置 群组
     * @param groupType
     */
    public void setGroupType(Integer groupType) {
        this.groupType = groupType;
    }

    private String inviteImage;

    /**
     * 获取 推广二维码图片
     * @return
     */
    public String getInviteImage() {
        return inviteImage;
    }

    /**
     * 设置 推广二维码图片
     * @param inviteImage
     */
    public void setInviteImage(String inviteImage) {
        this.inviteImage = inviteImage;
    }

    private Integer invitedAmount;

    /**
     * 获取 推广人数
     * @return
     */
    public Integer getInvitedAmount() {
        return invitedAmount;
    }

    /**
     * 设置 推广人数
     * @param invitedAmount
     */
    public void setInvitedAmount(Integer invitedAmount) {
        this.invitedAmount = invitedAmount;
    }

}