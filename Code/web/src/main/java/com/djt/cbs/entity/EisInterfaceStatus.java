package com.djt.cbs.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * EIS 接口状态表。
 * 
 * <p>
 * Table: <strong>eis_interface_status</strong>
 * <p>
 * <table class="er-mapping" cellspacing=0 cellpadding=0 style="border:solid 1 #666;padding:3px;">
 * <tr style="background-color:#ddd;Text-align:Left;">
 * <th nowrap>属性名</th>
 * <th nowrap>属性类型</th>
 * <th nowrap>字段名</th>
 * <th nowrap>字段类型</th>
 * <th nowrap>说明</th>
 * </tr>
 * <tr>
 * <td>id</td>
 * <td>{@link Integer}</td>
 * <td>id</td>
 * <td>int</td>
 * <td>&nbsp;</td>
 * </tr>
 * <tr>
 * <td>interfaceCode</td>
 * <td>{@link String}</td>
 * <td>interface_code</td>
 * <td>varchar</td>
 * <td>接口编号</td>
 * </tr>
 * <tr>
 * <td>lastTime</td>
 * <td>{@link Date}</td>
 * <td>last_time</td>
 * <td>datetime</td>
 * <td>最后的截至时间</td>
 * </tr>
 * <tr>
 * <td>lastId</td>
 * <td>{@link Integer}</td>
 * <td>last_id</td>
 * <td>int</td>
 * <td>最后的订单号</td>
 * </tr>
 * <tr>
 * <td>updateTime</td>
 * <td>{@link Date}</td>
 * <td>update_time</td>
 * <td>datetime</td>
 * <td>更新时间</td>
 * </tr>
 * </table>
 * 
 * @author 周智勇
 * @date 2015-4-15
 * @email 36316500@qq.com
 */
public class EisInterfaceStatus implements Serializable {
	/** Comment for <code>serialVersionUID</code> */
	private static final long serialVersionUID = -5863630144561759787L;
	/**
	 * 接口编码 - 从商城获取会员报名信息
	 */
	public static String INTERFACE_CODE_GET_ENTERED_FROM_SHOP = "get_entered_from_shop";

	/**
	 * 接口编码 - 将不属于团员的会员平均分配给每个团员
	 */
	public static String INTERFACE_CODE_DISTRIBUTE_MEMBER_TO_INVITER = "distribute_member_to_inviter";

	private Integer id;

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer value) {
		this.id = value;
	}

	private String interfaceCode;

	/**
	 * 获取 接口编号。
	 */
	public String getInterfaceCode() {
		return this.interfaceCode;
	}

	/**
	 * 设置 接口编号。
	 * 
	 * @param value
	 *            属性值
	 */
	public void setInterfaceCode(String value) {
		this.interfaceCode = value;
	}

	private Date lastTime;

	/**
	 * 获取 最后的截至时间。
	 */
	public Date getLastTime() {
		return this.lastTime;
	}

	/**
	 * 设置 最后的截至时间。
	 * 
	 * @param value
	 *            属性值
	 */
	public void setLastTime(Date value) {
		this.lastTime = value;
	}

	private Date now;

	public Date getNow() {
		return now;
	}

	public void setNow(Date now) {
		this.now = now;
	}

	private Date updateTime;

	/**
	 * 获取 更新时间。
	 */
	public Date getUpdateTime() {
		return this.updateTime;
	}

	/**
	 * 设置 更新时间。
	 * 
	 * @param value
	 *            属性值
	 */
	public void setUpdateTime(Date value) {
		this.updateTime = value;
	}

	private Integer lastId;

	/**
	 * 获取 最后更新id。
	 */
	public Integer getLastId() {
		return this.lastId;
	}

	/**
	 * 设置 最后更新id。
	 */
	public void setLastId(Integer value) {
		this.lastId = value;
	}
}