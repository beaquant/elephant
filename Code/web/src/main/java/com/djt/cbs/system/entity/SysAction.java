package com.djt.cbs.system.entity;

import java.io.Serializable;

/**
 * <p>Table: <strong>sys_action</strong>
 * <p><table class="er-mapping" cellspacing=0 cellpadding=0 style="border:solid 1 #666;padding:3px;">
 *   <tr style="background-color:#ddd;Text-align:Left;">
 *     <th nowrap>属性名</th><th nowrap>属性类型</th><th nowrap>字段名</th><th nowrap>字段类型</th><th nowrap>说明</th>
 *   </tr>
 *   <tr><td>actId</td><td>{@link Integer}</td><td>act_id</td><td>int</td><td>&nbsp;</td></tr>
 *   <tr><td>actKey</td><td>{@link String}</td><td>act_key</td><td>varchar</td><td>操作key值。<br />操作key值必须是一个唯一值，界面上可以根据key值查询用户是否有该操作权限，用来确定按钮是否显示之类。</td></tr>
 *   <tr><td>actName</td><td>{@link String}</td><td>act_name</td><td>varchar</td><td>&nbsp;</td></tr>
 *   <tr><td>fnId</td><td>{@link Integer}</td><td>fn_id</td><td>int</td><td>功能ID。</td></tr>
 *   <tr><td>status</td><td>{@link Integer}</td><td>status</td><td>int</td><td>状态。<br />-1逻辑删除，0禁用，1启用</td></tr>
 *   <tr><td>url</td><td>{@link String}</td><td>url</td><td>varchar</td><td>系统操作的URL，不包含域名、端口号部分。</td></tr>
 *   <tr><td>remark</td><td>{@link String}</td><td>remark</td><td>varchar</td><td>备注。</td></tr>
 * </table>
 *
 */
public class SysAction implements Serializable {
    /**
     *Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -6167952093955233428L;

    private Integer           actId;

    public Integer getActId() {
        return this.actId;
    }

    public void setActId(Integer value) {
        this.actId = value;
    }

    private String actKey;

    /**
     * 获取 操作key值。
     *
     * <p>
     * 操作key值必须是一个唯一值，界面上可以根据key值查询用户是否有该操作权限，用来确定按钮是否显示之类。
     */
    public String getActKey() {
        return this.actKey;
    }

    /**
     * 设置 操作key值。
     *
     * <p>
     * 操作key值必须是一个唯一值，界面上可以根据key值查询用户是否有该操作权限，用来确定按钮是否显示之类。
     *
     * @param value 属性值
     */
    public void setActKey(String value) {
        this.actKey = value;
    }

    private String actName;

    public String getActName() {
        return this.actName;
    }

    public void setActName(String value) {
        this.actName = value;
    }

    private Integer fnId = 0;

    /**
     * 获取 功能ID。
     */
    public Integer getFnId() {
        return this.fnId;
    }

    /**
     * 设置 功能ID。
     *
     * @param value 属性值
     */
    public void setFnId(Integer value) {
        this.fnId = value;
    }

    private String fnName;

    public String getFnName() {
        return this.fnName;
    }

    public void setFnName(String value) {
        this.fnName = value;
    }

    private Integer status = 1;

    /**
     * 获取 状态。
     *
     * <p>
     * -1逻辑删除，0禁用，1启用
     */
    public Integer getStatus() {
        return this.status;
    }

    /**
     * 设置 状态。
     *
     * <p>
     * -1逻辑删除，0禁用，1启用
     *
     * @param value 属性值
     */
    public void setStatus(Integer value) {
        this.status = value;
    }

    private String url;

    /**
     * 获取 系统操作的URL，不包含域名、端口号部分。
     */
    public String getUrl() {
        return this.url;
    }

    /**
     * 设置 系统操作的URL，不包含域名、端口号部分。
     *
     * @param value 属性值
     */
    public void setUrl(String value) {
        this.url = value;
    }

    private String remark = "";

    /**
     * 获取 备注。
     */
    public String getRemark() {
        return this.remark;
    }

    /**
     * 设置 备注。
     *
     * @param value 属性值
     */
    public void setRemark(String value) {
        this.remark = value;
    }

}