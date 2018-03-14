package com.djt.cbs.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 报名临时表。
 *
 * <p>Table: <strong>entered_temp</strong>
 * <p><table class="er-mapping" cellspacing=0 cellpadding=0 style="border:solid 1 #666;padding:3px;">
 *   <tr style="background-color:#ddd;Text-align:Left;">
 *     <th nowrap>属性名</th><th nowrap>属性类型</th><th nowrap>字段名</th><th nowrap>字段类型</th><th nowrap>说明</th>
 *   </tr>
 *   <tr><td>id</td><td>{@link Integer}</td><td>id</td><td>int</td><td>流水号</td></tr>
 *   <tr><td>name</td><td>{@link String}</td><td>name</td><td>varchar</td><td>姓名</td></tr>
 *   <tr><td>mobile</td><td>{@link String}</td><td>mobile</td><td>varchar</td><td>手机号</td></tr>
 *   <tr><td>xiaoqu</td><td>{@link String}</td><td>xiaoqu</td><td>varchar</td><td>小区</td></tr>
 *   <tr><td>qq</td><td>{@link String}</td><td>qq</td><td>varchar</td><td>&nbsp;</td></tr>
 *   <tr><td>jindu</td><td>{@link String}</td><td>jindu</td><td>varchar</td><td>&nbsp;</td></tr>
 *   <tr><td>pinlei</td><td>{@link String}</td><td>pinlei</td><td>varchar</td><td>&nbsp;</td></tr>
 *   <tr><td>groupType</td><td>{@link String}</td><td>group_type</td><td>char</td><td>&nbsp;</td></tr>
 *   <tr><td>enteredTime</td><td>{@link Date}</td><td>entered_time</td><td>datetime</td><td>&nbsp;</td></tr>
 * </table>
 *
 * @author 周智勇
 * @date 2015-4-3
 * @email 36316500@qq.com
 */
public class EnteredTemp implements Serializable {
    /** Comment for <code>serialVersionUID</code> */
    private static final long serialVersionUID = 759868180913144331L;

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

    private String xiaoqu;

    /**
     * 获取 小区。
     */
    public String getXiaoqu() {
        return this.xiaoqu;
    }

    /**
     * 设置 小区。
     *
     * @param value 属性值
     */
    public void setXiaoqu(String value) {
        this.xiaoqu = value;
    }

    private String qq;

    public String getQq() {
        return this.qq;
    }

    public void setQq(String value) {
        this.qq = value;
    }

    private String jindu;

    public String getJindu() {
        return this.jindu;
    }

    public void setJindu(String value) {
        this.jindu = value;
    }

    private String pinlei;

    public String getPinlei() {
        return this.pinlei;
    }

    public void setPinlei(String value) {
        this.pinlei = value;
    }

    private String groupType;

    public String getGroupType() {
        return groupType;
    }

    public void setGroupType(String groupType) {
        this.groupType = groupType;
    }

    private Date enteredTime;

    public Date getEnteredTime() {
        return enteredTime;
    }

    public void setEnteredTime(Date enteredTime) {
        this.enteredTime = enteredTime;
    }

}