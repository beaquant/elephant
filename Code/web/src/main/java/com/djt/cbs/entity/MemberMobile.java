package com.djt.cbs.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 会员手机表。
 *
 * <p>Table: <strong>member_mobile</strong>
 * <p><table class="er-mapping" cellspacing=0 cellpadding=0 style="border:solid 1 #666;padding:3px;">
 *   <tr style="background-color:#ddd;Text-align:Left;">
 *     <th nowrap>属性名</th><th nowrap>属性类型</th><th nowrap>字段名</th><th nowrap>字段类型</th><th nowrap>说明</th>
 *   </tr>
 *   <tr><td>id</td><td>{@link Integer}</td><td>id</td><td>int</td><td>流水号</td></tr>
 *   <tr><td>memberId</td><td>{@link Integer}</td><td>member_id</td><td>int</td><td>会员ID</td></tr>
 *   <tr><td>mobile</td><td>{@link String}</td><td>mobile</td><td>varchar</td><td>手机号</td></tr>
 *   <tr><td>createTime</td><td>{@link Date}</td><td>create_time</td><td>datetime</td><td>创建日期</td></tr>
 * </table>
 *
 * @author 周智勇
 * @date 2015-4-3
 * @email 36316500@qq.com
 */
public class MemberMobile implements Serializable {
    /** Comment for <code>serialVersionUID</code> */
    private static final long serialVersionUID = 5169148938247769026L;

    private Integer           id;

    /**
     * 获取 流水号。
     */
    public Integer getId() {
        return this.id;
    }

    /**
     * 设置 流水号。
     *
     * @param value 属性值
     */
    public void setId(Integer value) {
        this.id = value;
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

    private Date createTime;

    /**
     * 获取 创建日期。
     */
    public Date getCreateTime() {
        return this.createTime;
    }

    /**
     * 设置 创建日期。
     *
     * @param value 属性值
     */
    public void setCreateTime(Date value) {
        this.createTime = value;
    }

}