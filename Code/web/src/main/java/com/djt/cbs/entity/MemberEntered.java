package com.djt.cbs.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 会员报名表。
 *
 * <p>Table: <strong>member_entered</strong>
 * <p><table class="er-mapping" cellspacing=0 cellpadding=0 style="border:solid 1 #666;padding:3px;">
 *   <tr style="background-color:#ddd;Text-align:Left;">
 *     <th nowrap>属性名</th><th nowrap>属性类型</th><th nowrap>字段名</th><th nowrap>字段类型</th><th nowrap>说明</th>
 *   </tr>
 *   <tr><td>enteredId</td><td>{@link Integer}</td><td>entered_id</td><td>int</td><td>报名Id</td></tr>
 *   <tr><td>memberId</td><td>{@link Integer}</td><td>member_id</td><td>int</td><td>会员ID</td></tr>
 *   <tr><td>mobile</td><td>{@link String}</td><td>mobile</td><td>varchar</td><td>手机号</td></tr>
 *   <tr><td>name</td><td>{@link String}</td><td>name</td><td>varchar</td><td>姓名</td></tr>
 *   <tr><td>groupType</td><td>{@link String}</td><td>group_type</td><td>char</td><td>团购类型<br />HOU：房产<br />CAR：汽车<br />BME：建材<br />HEA：家电</td></tr>
 *   <tr><td>memberStatus</td><td>{@link Integer}</td><td>member_status</td><td>int</td><td>会员状态<br />0：已报名<br />10：已签到<br />50：已下单<br />100：已完成</td></tr>
 *   <tr><td>source</td><td>{@link String}</td><td>source</td><td>varchar</td><td>来源渠道<br />SC：商城<br />M_SC：移动商城<br />WX：微信服务号<br />HDXC：活动现场<br />DH：电话</td></tr>
 *   <tr><td>cityCode</td><td>{@link String}</td><td>city_code</td><td>varchar</td><td>城市编码</td></tr>
 *   <tr><td>cityName</td><td>{@link String}</td><td>city_name</td><td>varchar</td><td>城市名称</td></tr>
 *   <tr><td>createTime</td><td>{@link Date}</td><td>create_time</td><td>datetime</td><td>创建时间</td></tr>
 *   <tr><td>enteredTime</td><td>{@link Date}</td><td>entered_time</td><td>datetime</td><td>报名时间</td></tr>
 *   <tr><td>regionName1</td><td>{@link String}</td><td>region_name_1</td><td>varchar</td><td>区县名称1<br />适用于房产团购</td></tr>
 *   <tr><td>regionName2</td><td>{@link String}</td><td>region_name_2</td><td>varchar</td><td>区县名称<br />适用于房产团购</td></tr>
 *   <tr><td>regionName3</td><td>{@link String}</td><td>region_name_3</td><td>varchar</td><td>区县名称<br />适用于房产团购</td></tr>
 *   <tr><td>neighborhood1</td><td>{@link String}</td><td>neighborhood_1</td><td>varchar</td><td>小区名称<br />适用于建材、房产团购</td></tr>
 *   <tr><td>neighborhood2</td><td>{@link String}</td><td>neighborhood_2</td><td>varchar</td><td>小区名称<br />适用于建材、房产团购</td></tr>
 *   <tr><td>neighborhood3</td><td>{@link String}</td><td>neighborhood_3</td><td>varchar</td><td>小区名称<br />适用于建材、房产团购</td></tr>
 *   <tr><td>houseUnitPrice</td><td>{@link Integer}</td><td>house_unit_price</td><td>int</td><td>预期房产单价</td></tr>
 *   <tr><td>houseTotalPrice</td><td>{@link Integer}</td><td>house_total_price</td><td>int</td><td>预期房产总价</td></tr>
 *   <tr><td>carPriceArea</td><td>{@link Integer}</td><td>car_price_area</td><td>int</td><td>团购汽车价格区间</td></tr>
 *   <tr><td>brandName</td><td>{@link String}</td><td>brand_name</td><td>varchar</td><td>品牌名称<br />适用于汽车和家电</td></tr>
 *   <tr><td>qq</td><td>{@link String}</td><td>qq</td><td>varchar</td><td>QQ号码<br />适用于建材团购</td></tr>
 *   <tr><td>decoratingProgress</td><td>{@link String}</td><td>decorating_progress</td><td>varchar</td><td>装修进度<br />适用于建材团购</td></tr>
 *   <tr><td>decoratingStatus</td><td>{@link Integer}</td><td>decorating_status</td><td>varchar</td><td>装修进度<br />0：其它<br />10：还未交房<br />20：准备开工<br />30：水电工程<br />40：泥工工程<br />50：木工工程<br />60：油漆工程<br />70：家具家电<br />100：已完工</td></tr>
 *   <tr><td>productCategory</td><td>{@link String}</td><td>product_category</td><td>varchar</td><td>产品品类<br />使用于家电团购</td></tr>
 *   <tr><td>memberNote</td><td>{@link String}</td><td>member_note</td><td>varchar</td><td>&nbsp;</td></tr>
 *   <tr><td>customerServiceNote</td><td>{@link String}</td><td>customer_service_note</td><td>varchar</td><td>客服备注</td></tr>
 *   <tr><td>changeTime</td><td>{@link Date}</td><td>change_time</td><td>datetime</td><td>最后修改时间</td></tr>
 *   <tr><td>changeUser</td><td>{@link String}</td><td>change_user</td><td>varchar</td><td>最后修改人</td></tr>
 *   <tr><td>inviterId</td><td>{@link Integer}</td><td>inviter_id</td><td>int</td><td>邀请人Id</td></tr>
 *   <tr><td>isPublic</td><td>{@link Integer}</td><td>is_public</td><td>int</td><td>是否公共资源</td></tr>
 * </table>
 *
 * @author 周智勇
 * @date 2015-3-26
 * @email 36316500@qq.com
 */
public class MemberEntered implements Serializable {
    /** Comment for <code>serialVersionUID</code> */
    private static final long   serialVersionUID                     = -9058850047289988556L;

    /**
     * 会员状态 -  已报名
     */
    public static final Integer MEMBER_STATUS_ENTERRED               = 0;
    /**
     * 会员状态 -  已签到
     */
    public static final Integer MEMBER_STATUS_SIGNUP                 = 10;
    /**
     * 会员状态 -  已下单
     */
    public static final Integer MEMBER_STATUS_ORDERED                = 50;
    /**
     * 会员状态 -  已关闭
     */
    public static final Integer MEMBER_STATUS_CLOSED                 = 100;

    /**
     * 来源渠道 -  商城
     */
    public static final String  SOURCE_SC                            = "SC";
    /**
     * 来源渠道 -  移动商城
     */
    public static final String  SOURCE_M_SC                          = "M_SC";
    /**
     * 来源渠道 -  微信
     */
    public static final String  SOURCE_WX                            = "WX";
    /**
     * 来源渠道 -  活动现场
     */
    public static final String  SOURCE_HDXC                          = "HDXC";
    /**
     * 来源渠道 -  电话
     */
    public static final String  SOURCE_DH                            = "DH";

    /**
     * 装修状态 - 其它
     */
    public static final Integer DECORATING_STATUS_OTHER              = 0;
    /**
     * 装修状态 - 还未交房
     */
    public static final Integer DECORATING_STATUS_HOUSE_NOT_ACCEPTED = 10;
    /**
     * 装修状态 - 准备开工
     */
    public static final Integer DECORATING_STATUS_PREPARE            = 20;
    /**
     * 装修状态 - 水电工程
     */
    public static final Integer DECORATING_STATUS_POWER_AND_WATER    = 30;
    /**
     * 装修状态 - 泥工工程
     */
    public static final Integer DECORATING_STATUS_TROWELLING         = 40;
    /**
     * 装修状态 - 木工工程
     */
    public static final Integer DECORATING_STATUS_WOODWORKING        = 50;
    /**
     * 装修状态 - 油漆工程
     */
    public static final Integer DECORATING_STATUS_PAINTING           = 60;
    /**
     * 装修状态 - 家具家电
     */
    public static final Integer DECORATING_STATUS_FURNITRUE_AND_HEA  = 70;
    /**
     * 装修状态 - 已完工
     */
    public static final Integer DECORATING_STATUS_FINISHED           = 100;

    private Integer             enteredId;

    /**
     * 获取 报名Id。
     */
    public Integer getEnteredId() {
        return this.enteredId;
    }

    /**
     * 设置 报名Id。
     *
     * @param value 属性值
     */
    public void setEnteredId(Integer value) {
        this.enteredId = value;
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

    private Integer memberStatus;

    /**
     * 获取 会员状态。
     *
     * <p>
     * 0：已报名<br />
     * 10：已签到<br />
     * 50：已下单<br />
     * 100：已完成
     */
    public Integer getMemberStatus() {
        return this.memberStatus;
    }

    /**
     * 设置 会员状态。
     *
     * <p>
     * 0：已报名<br />
     * 10：已签到<br />
     * 50：已下单<br />
     * 100：已完成
     *
     * @param value 属性值
     */
    public void setMemberStatus(Integer value) {
        this.memberStatus = value;
    }

    private String source;

    /**
     * 获取 来源渠道。
     *
     * <p>
     * SC：商城<br />
     * M_SC：移动商城<br />
     * WX：微信服务号<br />
     * HDXC：活动现场<br />
     * DH：电话
     */
    public String getSource() {
        return this.source;
    }

    /**
     * 设置 来源渠道。
     *
     * <p>
     * SC：商城<br />
     * M_SC：移动商城<br />
     * WX：微信服务号<br />
     * HDXC：活动现场<br />
     * DH：电话
     *
     * @param value 属性值
     */
    public void setSource(String value) {
        this.source = value;
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

    private Date enteredTime;

    /**
     * 获取 报名时间。
     */
    public Date getEnteredTime() {
        return this.enteredTime;
    }

    /**
     * 设置 报名时间。
     *
     * @param value 属性值
     */
    public void setEnteredTime(Date value) {
        this.enteredTime = value;
    }

    private String regionName1;

    /**
     * 获取 区县名称1。
     *
     * <p>
     * 适用于房产团购
     */
    public String getRegionName1() {
        return this.regionName1;
    }

    /**
     * 设置 区县名称1。
     *
     * <p>
     * 适用于房产团购
     *
     * @param value 属性值
     */
    public void setRegionName1(String value) {
        this.regionName1 = value;
    }

    private String regionName2;

    /**
     * 获取 区县名称。
     *
     * <p>
     * 适用于房产团购
     */
    public String getRegionName2() {
        return this.regionName2;
    }

    /**
     * 设置 区县名称。
     *
     * <p>
     * 适用于房产团购
     *
     * @param value 属性值
     */
    public void setRegionName2(String value) {
        this.regionName2 = value;
    }

    private String regionName3;

    /**
     * 获取 区县名称。
     *
     * <p>
     * 适用于房产团购
     */
    public String getRegionName3() {
        return this.regionName3;
    }

    /**
     * 设置 区县名称。
     *
     * <p>
     * 适用于房产团购
     *
     * @param value 属性值
     */
    public void setRegionName3(String value) {
        this.regionName3 = value;
    }

    private String neighborhood1;

    /**
     * 获取 小区名称。
     *
     * <p>
     * 适用于建材、房产团购
     */
    public String getNeighborhood1() {
        return this.neighborhood1;
    }

    /**
     * 设置 小区名称。
     *
     * <p>
     * 适用于建材、房产团购
     *
     * @param value 属性值
     */
    public void setNeighborhood1(String value) {
        this.neighborhood1 = value;
    }

    private String neighborhood2;

    /**
     * 获取 小区名称。
     *
     * <p>
     * 适用于建材、房产团购
     */
    public String getNeighborhood2() {
        return this.neighborhood2;
    }

    /**
     * 设置 小区名称。
     *
     * <p>
     * 适用于建材、房产团购
     *
     * @param value 属性值
     */
    public void setNeighborhood2(String value) {
        this.neighborhood2 = value;
    }

    private String neighborhood3;

    /**
     * 获取 小区名称。
     *
     * <p>
     * 适用于建材、房产团购
     */
    public String getNeighborhood3() {
        return this.neighborhood3;
    }

    /**
     * 设置 小区名称。
     *
     * <p>
     * 适用于建材、房产团购
     *
     * @param value 属性值
     */
    public void setNeighborhood3(String value) {
        this.neighborhood3 = value;
    }

    private Integer houseUnitPrice = 0;

    /**
     * 获取 预期房产单价。
     */
    public Integer getHouseUnitPrice() {
        return this.houseUnitPrice;
    }

    /**
     * 设置 预期房产单价。
     *
     * @param value 属性值
     */
    public void setHouseUnitPrice(Integer value) {
        this.houseUnitPrice = value;
    }

    private Integer houseTotalPrice = 0;

    /**
     * 获取 预期房产总价。
     */
    public Integer getHouseTotalPrice() {
        return this.houseTotalPrice;
    }

    /**
     * 设置 预期房产总价。
     *
     * @param value 属性值
     */
    public void setHouseTotalPrice(Integer value) {
        this.houseTotalPrice = value;
    }

    private Integer carPriceArea = 0;

    /**
     * 获取 团购汽车价格区间。
     */
    public Integer getCarPriceArea() {
        return this.carPriceArea;
    }

    /**
     * 设置 团购汽车价格区间。
     *
     * @param value 属性值
     */
    public void setCarPriceArea(Integer value) {
        this.carPriceArea = value;
    }

    private String brandName;

    /**
     * 获取 品牌名称。
     *
     * <p>
     * 适用于汽车和家电
     */
    public String getBrandName() {
        return this.brandName;
    }

    /**
     * 设置 品牌名称。
     *
     * <p>
     * 适用于汽车和家电
     *
     * @param value 属性值
     */
    public void setBrandName(String value) {
        this.brandName = value;
    }

    private String qq;

    /**
     * 获取 QQ号码。
     *
     * <p>
     * 适用于建材团购
     */
    public String getQq() {
        return this.qq;
    }

    /**
     * 设置 QQ号码。
     *
     * <p>
     * 适用于建材团购
     *
     * @param value 属性值
     */
    public void setQq(String value) {
        this.qq = value;
    }

    private String decoratingProgress;

    /**
     * 获取 装修进度。
     *
     * <p>
     * 适用于建材团购
     */
    public String getDecoratingProgress() {
        return this.decoratingProgress;
    }

    /**
     * 设置 装修进度。
     *
     * <p>
     * 适用于建材团购
     *
     * @param value 属性值
     */
    public void setDecoratingProgress(String value) {
        this.decoratingProgress = value;
    }

    private Integer decoratingStatus;

    /**
     * 获取 装修状态
     * 0：其它
     * 10：还未交房
     * 20：准备开工
     * 30：水电工程
     * 40：泥工工程
     * 50：木工工程
     * 60：油漆工程
     * 70：家具家电
     * 100：已完工
     * @return
     */
    public Integer getDecoratingStatus() {
        return decoratingStatus;
    }

    /**
     * 设置 装修状态
     * 0：其它
     * 10：还未交房
     * 20：准备开工
     * 30：水电工程
     * 40：泥工工程
     * 50：木工工程
     * 60：油漆工程
     * 70：家具家电
     * 100：已完工
     * @return
     */
    public void setDecoratingStatus(Integer decoratingStatus) {
        this.decoratingStatus = decoratingStatus;
    }

    private String productCategory;

    /**
     * 获取 产品品类。
     *
     * <p>
     * 使用于家电团购
     */
    public String getProductCategory() {
        return this.productCategory;
    }

    /**
     * 设置 产品品类。
     *
     * <p>
     * 使用于家电团购
     *
     * @param value 属性值
     */
    public void setProductCategory(String value) {
        this.productCategory = value;
    }

    private String memberNote;

    public String getMemberNote() {
        return this.memberNote;
    }

    public void setMemberNote(String value) {
        this.memberNote = value;
    }

    private String customerServiceNote;

    /**
     * 获取 客服备注。
     */
    public String getCustomerServiceNote() {
        return this.customerServiceNote;
    }

    /**
     * 设置 客服备注。
     *
     * @param value 属性值
     */
    public void setCustomerServiceNote(String value) {
        this.customerServiceNote = value;
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

    private Integer inviterId;

    /**
     * 获取 邀请人ID
     * @return
     */
    public Integer getInviterId() {
        return inviterId;
    }

    /**
     * 设置 邀请人ID
     * @param inviterId
     */
    public void setInviterId(Integer inviterId) {
        this.inviterId = inviterId;
    }

    private Integer isPublic = 0;

    /**
     * 获取 是否公共资源
     * @return
     */
    public Integer getIsPublic() {
        return isPublic;
    }

    /**
     * 设置 是否公共资源
     * @param isPublic
     */
    public void setIsPublic(Integer isPublic) {
        this.isPublic = isPublic;
    }

}