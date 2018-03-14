package com.djt.cbs.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户邀请表。
 *
 * <p>Table: <strong>user_invited</strong>
 * <p><table class="er-mapping" cellspacing=0 cellpadding=0 style="border:solid 1 #666;padding:3px;">
 *   <tr style="background-color:#ddd;Text-align:Left;">
 *     <th nowrap>属性名</th><th nowrap>属性类型</th><th nowrap>字段名</th><th nowrap>字段类型</th><th nowrap>说明</th>
 *   </tr>
 *   <tr><td>id</td><td>{@link Integer}</td><td>id</td><td>int</td><td>流水ID</td></tr>
 *   <tr><td>userId</td><td>{@link Integer}</td><td>user_id</td><td>int</td><td>用户ID<br />从880000000000开始</td></tr>
 *   <tr><td>invitedOpenId</td><td>{@link String}</td><td>InvitedOpenId</td><td>varchar</td><td>被邀请者OpenId</td></tr>
 *   <tr><td>createTime</td><td>{@link Date}</td><td>create_time</td><td>datetime</td><td>创建时间</td></tr>
 * </table>
 *
 * @author 周智勇
 * @date 2015-5-22
 * @email 36316500@qq.com
 */
public class UserInvited implements Serializable {
    /** Comment for <code>serialVersionUID</code> */
    private static final long serialVersionUID = -8875600968607821707L;

    private Integer           id;

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

    private Integer userId;

    /**
     * 获取 用户ID。
     *
     * <p>
     * 从880000000000开始
     */
    public Integer getUserId() {
        return this.userId;
    }

    /**
     * 设置 用户ID。
     *
     * <p>
     * 从880000000000开始
     *
     * @param value 属性值
     */
    public void setUserId(Integer value) {
        this.userId = value;
    }

    private String invitedOpenId;

    /**
     * 获取 被邀请者OpenId。
     */
    public String getInvitedOpenId() {
        return this.invitedOpenId;
    }

    /**
     * 设置 被邀请者OpenId。
     *
     * @param value 属性值
     */
    public void setInvitedOpenId(String value) {
        this.invitedOpenId = value;
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

}