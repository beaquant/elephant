package com.djt.cbs.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 购买队列。
 *
 * <p>Table: <strong>purchase_queue</strong>
 * <p><table class="er-mapping" cellspacing=0 cellpadding=0 style="border:solid 1 #666;padding:3px;">
 *   <tr style="background-color:#ddd;Text-align:Left;">
 *     <th nowrap>属性名</th><th nowrap>属性类型</th><th nowrap>字段名</th><th nowrap>字段类型</th><th nowrap>说明</th>
 *   </tr>
 *   <tr><td>id</td><td>{@link Integer}</td><td>id</td><td>int</td><td>流水号</td></tr>
 *   <tr><td>userId</td><td>{@link Integer}</td><td>user_id</td><td>int</td><td>用户ID<br />从880000000000开始</td></tr>
 *   <tr><td>status</td><td>{@link Integer}</td><td>status</td><td>int</td><td>状态<br />0：未分配<br />1：已分配</td></tr>
 *   <tr><td>enteredTime</td><td>{@link Date}</td><td>entered_time</td><td>datetime</td><td>进入时间</td></tr>
 * </table>
 *
 * @author 周智勇
 * @date 2015-5-22
 * @email 36316500@qq.com
 */
public class PurchaseQueue implements Serializable {

    /** Comment for <code>serialVersionUID</code> */
    private static final long serialVersionUID = -5747053466025134609L;

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

    private Integer status;

    /**
     * 获取 状态。
     *
     * <p>
     * 0：未分配<br />
     * 1：已分配
     */
    public Integer getStatus() {
        return this.status;
    }

    /**
     * 设置 状态。
     *
     * <p>
     * 0：未分配<br />
     * 1：已分配
     *
     * @param value 属性值
     */
    public void setStatus(Integer value) {
        this.status = value;
    }

    private Date enteredTime;

    /**
     * 获取 进入时间。
     */
    public Date getEnteredTime() {
        return this.enteredTime;
    }

    /**
     * 设置 进入时间。
     *
     * @param value 属性值
     */
    public void setEnteredTime(Date value) {
        this.enteredTime = value;
    }

}