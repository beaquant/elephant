package com.djt.cbs.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 团购活动表。
 *
 * <p>Table: <strong>group_purchase</strong>
 * <p><table class="er-mapping" cellspacing=0 cellpadding=0 style="border:solid 1 #666;padding:3px;">
 *   <tr style="background-color:#ddd;Text-align:Left;">
 *     <th nowrap>属性名</th><th nowrap>属性类型</th><th nowrap>字段名</th><th nowrap>字段类型</th><th nowrap>说明</th>
 *   </tr>
 *   <tr><td>gpId</td><td>{@link Integer}</td><td>gp_id</td><td>int</td><td>&nbsp;</td></tr>
 *   <tr><td>activityName</td><td>{@link String}</td><td>activity_name</td><td>varchar</td><td>活动名称</td></tr>
 *   <tr><td>groupType</td><td>{@link String}</td><td>group_type</td><td>char</td><td>团购类型<br />HOU：房产<br />CAR：汽车<br />BME：建材<br />HEA：家电</td></tr>
 *   <tr><td>cityCode</td><td>{@link String}</td><td>city_code</td><td>varchar</td><td>城市编码</td></tr>
 *   <tr><td>cityName</td><td>{@link String}</td><td>city_name</td><td>varchar</td><td>城市名称</td></tr>
 *   <tr><td>activityStartDate</td><td>{@link Date}</td><td>activity_start_date</td><td>datetime</td><td>活动开始日期</td></tr>
 *   <tr><td>activityFinishDate</td><td>{@link Date}</td><td>activity_finish_date</td><td>datetime</td><td>活动结束日期</td></tr>
 *   <tr><td>activityStatus</td><td>{@link Integer}</td><td>activity_status</td><td>int</td><td>活动状态<br />-10：已取消<br />-20：已删除<br />0：报名中<br />10：进行中<br />50：已结束<br /></td></tr>
 *   <tr><td>createTime</td><td>{@link Date}</td><td>create_time</td><td>datetime</td><td>创建时间</td></tr>
 *   <tr><td>createUser</td><td>{@link String}</td><td>create_user</td><td>varchar</td><td>创建人</td></tr>
 *   <tr><td>changeTime</td><td>{@link Date}</td><td>change_time</td><td>datetime</td><td>最后修改时间</td></tr>
 *   <tr><td>changeUser</td><td>{@link String}</td><td>change_user</td><td>varchar</td><td>最后修改人</td></tr>
 *   <tr><td>qrCode</td><td>{@link String}</td><td>qr_code</td><td>varchar</td><td>二维码微信的二维码ticket</td></tr>
 *   <tr><td>gpImage</td><td>{@link String}</td><td>gp_image</td><td>varchar</td><td>活动签到的二维码图片</td></tr>
 * </table>
 *
 * @author 周智勇
 * @date 2015-3-25
 * @email 36316500@qq.com
 */
public class GroupPurchase implements Serializable {
    /** Comment for <code>serialVersionUID</code> */
    private static final long   serialVersionUID         = -4848482300154392162L;

    /**
     * 团购类型 - 房产
     */
    public static final String  GROUP_TYPE_HOU           = "HOU";
    /**
     * 团购类型 - 汽车
     */
    public static final String  GROUP_TYPE_CAR           = "CAR";
    /**
     * 团购类型 - 建材
     */
    public static final String  GROUP_TYPE_BME           = "BME";
    /**
     * 团购类型 - 家电
     */
    public static final String  GROUP_TYPE_HEA           = "HEA";

    /**
     * 活动状态 - 已取消
     */
    public static final Integer ACTIVITY_STATUS_CANCEL   = -10;
    /**
     * 活动状态 - 已删除
     */
    public static final Integer ACTIVITY_STATUS_DELETED  = -20;
    /**
     * 活动状态 - 报名中
     */
    public static final Integer ACTIVITY_STATUS_ENTERING = 0;
    /**
     * 活动状态 - 举办中
     */
    public static final Integer ACTIVITY_STATUS_RUNNING  = 10;
    /**
     * 活动状态 - 已结束
     */
    public static final Integer ACTIVITY_STATUS_FINISHED = 50;

    private Integer             gpId;

    public Integer getGpId() {
        return this.gpId;
    }

    public void setGpId(Integer value) {
        this.gpId = value;
    }

    private String activityName;

    /**
     * 获取 活动名称。
     */
    public String getActivityName() {
        return this.activityName;
    }

    /**
     * 设置 活动名称。
     *
     * @param value 属性值
     */
    public void setActivityName(String value) {
        this.activityName = value;
    }

    private String groupType;

    /**
     * 获取 团购类型。
     *
     * <p>
     * HOU：房产<br />
     * CAR：汽车<br />
     * BME：建材<br />
     * HEA：家电
     */
    public String getGroupType() {
        return this.groupType;
    }

    /**
     * 设置 团购类型。
     *
     * <p>
     * HOU：房产<br />
     * CAR：汽车<br />
     * BME：建材<br />
     * HEA：家电
     *
     * @param value 属性值
     */
    public void setGroupType(String value) {
        this.groupType = value;
    }

    private String cityCode;

    /**
     * 获取 城市编码。
     */
    public String getCityCode() {
        return this.cityCode;
    }

    /**
     * 设置 城市编码。
     *
     * @param value 属性值
     */
    public void setCityCode(String value) {
        this.cityCode = value;
    }

    private String cityName;

    /**
     * 获取 城市名称。
     */
    public String getCityName() {
        return this.cityName;
    }

    /**
     * 设置 城市名称。
     *
     * @param value 属性值
     */
    public void setCityName(String value) {
        this.cityName = value;
    }

    private Date activityStartDate;

    /**
     * 获取 活动开始日期。
     */
    public Date getActivityStartDate() {
        return this.activityStartDate;
    }

    /**
     * 设置 活动开始日期。
     *
     * @param value 属性值
     */
    public void setActivityStartDate(Date value) {
        this.activityStartDate = value;
    }

    private Date activityFinishDate;

    /**
     * 获取 活动结束日期。
     */
    public Date getActivityFinishDate() {
        return this.activityFinishDate;
    }

    /**
     * 设置 活动结束日期。
     *
     * @param value 属性值
     */
    public void setActivityFinishDate(Date value) {
        this.activityFinishDate = value;
    }

    private Integer activityStatus = 0;

    /**
     * 获取 活动状态。
     *
     * <p>
     * -10：已取消<br />
     * -20：已删除<br />
     * 0：报名中<br />
     * 10：举办中<br />
     * 50：已结束<br />
     * 
     */
    public Integer getActivityStatus() {
        return this.activityStatus;
    }

    /**
     * 设置 活动状态。
     *
     * <p>
     * -10：已取消<br />
     * -20：已删除<br />
     * 0：报名中<br />
     * 10：举办中<br />
     * 50：已结束<br />
     * 
     *
     * @param value 属性值
     */
    public void setActivityStatus(Integer value) {
        this.activityStatus = value;
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

    private Date changeTime;

    /**
     * 获取 最后修改时间。
     */
    public Date getChangeTime() {
        return this.changeTime;
    }

    /**
     * 设置 最后修改时间。
     *
     * @param value 属性值
     */
    public void setChangeTime(Date value) {
        this.changeTime = value;
    }

    private String changeUser;

    /**
     * 获取 最后修改人。
     */
    public String getChangeUser() {
        return this.changeUser;
    }

    /**
     * 设置 最后修改人。
     *
     * @param value 属性值
     */
    public void setChangeUser(String value) {
        this.changeUser = value;
    }
    
    private String qrCode;
    /**
     * 获取 二维码微信的二维码ticket
     * @return
     */
	public String getQrCode() {
		return qrCode;
	}
	/**
	 * 设置 二维码微信的二维码ticket
	 * @param qrCode
	 */
	public void setQrCode(String qrCode) {
		this.qrCode = qrCode;
	}
    
    private String gpImage;
    /**
     * 获取活动签到的二维码图片
     */
	public String getGpImage() {
		return gpImage;
	}
	/**
	 * 设置活动签到的二维码图片
	 * @param gpImage
	 */
	public void setGpImage(String gpImage) {
		this.gpImage = gpImage;
	}
    
    
    
    
}