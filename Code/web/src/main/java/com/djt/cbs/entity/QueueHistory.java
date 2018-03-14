package com.djt.cbs.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 队列历史。
 *
 * <p>Table: <strong>queue_history</strong>
 * <p><table class="er-mapping" cellspacing=0 cellpadding=0 style="border:solid 1 #666;padding:3px;">
 *   <tr style="background-color:#ddd;Text-align:Left;">
 *     <th nowrap>属性名</th><th nowrap>属性类型</th><th nowrap>字段名</th><th nowrap>字段类型</th><th nowrap>说明</th>
 *   </tr>
 *   <tr><td>id</td><td>{@link Integer}</td><td>id</td><td>int</td><td>流水号</td></tr>
 *   <tr><td>userId</td><td>{@link Integer}</td><td>user_id</td><td>int</td><td>用户ID<br />从880000000000开始</td></tr>
 *   <tr><td>queueType</td><td>{@link Integer}</td><td>queue_type</td><td>int</td><td>队列类型<br />1：进入购买队列<br />2：进入排队队列<br />3：移出购买队列<br />4：移出排队队列</td></tr>
 *   <tr><td>createTime</td><td>{@link Date}</td><td>create_time</td><td>datetime</td><td>创建时间</td></tr>
 * </table>
 *
 * @author 周智勇
 * @date 2015-5-22
 * @email 36316500@qq.com
 */
public class QueueHistory implements Serializable {
    /** Comment for <code>serialVersionUID</code> */
    private static final long   serialVersionUID        = -554707219899478174L;

    /**
     * 队列类型 - 进入购买队列
     */
    public static final Integer QUEUE_TYPE_IN_PURCHASE  = 1;
    /**
     * 队列类型 - 进入排队队列
     */
    public static final Integer QUEUE_TYPE_IN_WAITING   = 2;
    /**
     * 队列类型 - 移出购买队列
     */
    public static final Integer QUEUE_TYPE_OUT_PURCHASE = 3;
    /**
     * 队列类型 - 移出排队队列
     */
    public static final Integer QUEUE_TYPE_OUT_WAITING  = 4;

    private Integer             id;

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

    private Integer queueType;

    /**
     * 获取 队列类型。
     *
     * <p>
     * 1：进入购买队列<br />
     * 2：进入排队队列<br />
     * 3：移出购买队列<br />
     * 4：移出排队队列
     */
    public Integer getQueueType() {
        return this.queueType;
    }

    /**
     * 设置 队列类型。
     *
     * <p>
     * 1：进入购买队列<br />
     * 2：进入排队队列<br />
     * 3：移出购买队列<br />
     * 4：移出排队队列
     *
     * @param value 属性值
     */
    public void setQueueType(Integer value) {
        this.queueType = value;
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