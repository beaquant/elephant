package com.djt.cbs.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 待生成推广图片列表。
 *
 * <p>Table: <strong>invite_image_queue</strong>
 * <p><table class="er-mapping" cellspacing=0 cellpadding=0 style="border:solid 1 #666;padding:3px;">
 *   <tr style="background-color:#ddd;Text-align:Left;">
 *     <th nowrap>属性名</th><th nowrap>属性类型</th><th nowrap>字段名</th><th nowrap>字段类型</th><th nowrap>说明</th>
 *   </tr>
 *   <tr><td>id</td><td>{@link Integer}</td><td>id</td><td>int</td><td>&nbsp;</td></tr>
 *   <tr><td>userId</td><td>{@link Integer}</td><td>user_id</td><td>int</td><td>用户ID</td></tr>
 *   <tr><td>status</td><td>{@link Integer}</td><td>status</td><td>int</td><td>状态<br />0：未生成<br />1：已生成<br /></td></tr>
 *   <tr><td>createTime</td><td>{@link Date}</td><td>create_time</td><td>datetime</td><td>创建时间</td></tr>
 *   <tr><td>processTime</td><td>{@link Date}</td><td>process_time</td><td>datetime</td><td>处理时间</td></tr>
 * </table>
 *
 * @author 周智勇
 * @date 2015-5-23
 * @email 36316500@qq.com
 */
public class InviteImageQueue implements Serializable {
    /** Comment for <code>serialVersionUID</code> */
    private static final long serialVersionUID = -1427376479845888998L;

    private Integer           id;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer value) {
        this.id = value;
    }

    private Integer userId;

    /**
     * 获取 用户ID。
     *
     * <p>
     */
    public Integer getUserId() {
        return this.userId;
    }

    /**
     * 设置 用户ID。
     *
     * <p>
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
     * 0：未生成<br />
     * 1：已生成<br />
     * 
     */
    public Integer getStatus() {
        return this.status;
    }

    /**
     * 设置 状态。
     *
     * <p>
     * 0：未生成<br />
     * 1：已生成<br />
     * 
     *
     * @param value 属性值
     */
    public void setStatus(Integer value) {
        this.status = value;
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

    private Date processTime;

    /**
     * 获取 处理时间。
     */
    public Date getProcessTime() {
        return this.processTime;
    }

    /**
     * 设置 处理时间。
     *
     * @param value 属性值
     */
    public void setProcessTime(Date value) {
        this.processTime = value;
    }

}