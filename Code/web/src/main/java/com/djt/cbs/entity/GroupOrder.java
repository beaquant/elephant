package com.djt.cbs.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 团购订单。
 *
 * <p>Table: <strong>group_order</strong>
 * <p><table class="er-mapping" cellspacing=0 cellpadding=0 style="border:solid 1 #666;padding:3px;">
 *   <tr style="background-color:#ddd;Text-align:Left;">
 *     <th nowrap>属性名</th><th nowrap>属性类型</th><th nowrap>字段名</th><th nowrap>字段类型</th><th nowrap>说明</th>
 *   </tr>
 *   <tr><td>groupOrderId</td><td>{@link Integer}</td><td>group_order_id</td><td>int</td><td>订单ID<br />流水号</td></tr>
 *   <tr><td>groupOrderNo</td><td>{@link String}</td><td>group_order_no</td><td>varchar</td><td>订单编号<br />T+YYYYMMDD+5位流水号，预留1位扩展</td></tr>
 *   <tr><td>gpId</td><td>{@link Integer}</td><td>gp_id</td><td>int</td><td>&nbsp;</td></tr>
 *   <tr><td>memberId</td><td>{@link Integer}</td><td>member_id</td><td>int</td><td>会员ID</td></tr>
 *   <tr><td>groupType</td><td>{@link String}</td><td>group_type</td><td>char</td><td>团购类型<br />HOU：房产<br />CAR：汽车<br />BME：建材<br />HEA：家电</td></tr>
 *   <tr><td>cityCode</td><td>{@link String}</td><td>city_code</td><td>varchar</td><td>城市编码</td></tr>
 *   <tr><td>cityName</td><td>{@link String}</td><td>city_name</td><td>varchar</td><td>城市名称</td></tr>
 *   <tr><td>mobile</td><td>{@link String}</td><td>mobile</td><td>varchar</td><td>收货人手机号</td></tr>
 *   <tr><td>name</td><td>{@link String}</td><td>name</td><td>varchar</td><td>收货人姓名</td></tr>
 *   <tr><td>neighborhood</td><td>{@link String}</td><td>neighborhood</td><td>varchar</td><td>收货地址</td></tr>
 *   <tr><td>inviterId</td><td>{@link Integer}</td><td>inviter_id</td><td>int</td><td>邀请人ID</td></tr>
 *   <tr><td>orderStatus</td><td>{@link Integer}</td><td>order_status</td><td>int</td><td>订单状态<br />0：新创建<br />30：已付定金<br />31：已退单<br />50：已付尾款<br />100：已结单</td></tr>
 *   <tr><td>source</td><td>{@link String}</td><td>source</td><td>varchar</td><td>来源渠道<br />SC：商城<br />M_SC：移动商城<br />WX：微信服务号<br />HDXC：活动现场<br />DH：电话</td></tr>
 *   <tr><td>goodsNote</td><td>{@link String}</td><td>goods_note</td><td>varchar</td><td>商品描述</td></tr>
 *   <tr><td>depositAmount</td><td>{@link BigDecimal}</td><td>deposit_amount</td><td>decimal</td><td>定金金额</td></tr>
 *   <tr><td>depositPayTime</td><td>{@link Date}</td><td>deposit_pay_time</td><td>datetime</td><td>定金支付时间</td></tr>
 *   <tr><td>depositReturnUser</td><td>{@link String}</td><td>deposit_return_user</td><td>varchar</td><td>退单人</td></tr>
 *   <tr><td>depositReturnTime</td><td>{@link Date}</td><td>deposit_return_time</td><td>datetime</td><td>定金退单时间</td></tr>
 *   <tr><td>totalAmount</td><td>{@link BigDecimal}</td><td>total_amount</td><td>decimal</td><td>订单总金额</td></tr>
 *   <tr><td>createTime</td><td>{@link Date}</td><td>create_time</td><td>datetime</td><td>下单时间</td></tr>
 *   <tr><td>createUser</td><td>{@link String}</td><td>create_user</td><td>varchar</td><td>下单人</td></tr>
 *   <tr><td>updateTime</td><td>{@link Date}</td><td>update_time</td><td>datetime</td><td>修改时间</td></tr>
 *   <tr><td>updateUser</td><td>{@link String}</td><td>update_user</td><td>varchar</td><td>修改人</td></tr>
 *   <tr><td>note</td><td>{@link String}</td><td>note</td><td>varchar</td><td>备注</td></tr>
 *   <tr><td>isPublic</td><td>{@link Integer}</td><td>is_public</td><td>int</td><td>是否公共资源</td></tr>
 *   <tr><td>registedTime</td><td>{@link Date}</td><td>registed_time</td><td>datetime</td><td>会员注册时间</td></tr>
 * </table>
 *
 * @author 周智勇
 * @date 2015-5-1
 * @email 36316500@qq.com
 */
public class GroupOrder implements Serializable {
    /** Comment for <code>serialVersionUID</code> */
    private static final long   serialVersionUID                = 3754650950432522930L;

    /**
     * 订单状态 - 新创建
     */
    public static final Integer ORDER_STATUS_NEW                = 0;
    /**
     * 订单状态 - 已付定金
     */
    public static final Integer ORDER_STATUS_DEPOSIT_PAID       = 30;
    /**
     * 订单状态 - 已申请退定金
     */
    public static final Integer ORDER_STATUS_REQ_DEPOSIT_RETURN = 31;
    /**
     * 订单状态 - 已退定金
     */
    public static final Integer ORDER_STATUS_DEPOSIT_RETURNED   = 35;
    /**
     * 订单状态 - 已付全款
     */
    public static final Integer ORDER_STATUS_TOTAL_PAID         = 50;
    /**
     * 订单状态 - 已结单
     */
    public static final Integer ORDER_STATUS_FINISHED           = 100;

    private Integer             groupOrderId;

    /**
     * 获取 订单ID。
     *
     * <p>
     * 流水号
     */
    public Integer getGroupOrderId() {
        return this.groupOrderId;
    }

    /**
     * 设置 订单ID。
     *
     * <p>
     * 流水号
     *
     * @param value 属性值
     */
    public void setGroupOrderId(Integer value) {
        this.groupOrderId = value;
    }

    private String groupOrderNo;

    /**
     * 获取 订单编号。
     *
     * <p>
     * T+YYMMDD+5位流水号，预留3位扩展
     */
    public String getGroupOrderNo() {
        return this.groupOrderNo;
    }

    /**
     * 设置 订单编号。
     *
     * <p>
     * T+YYMMDD+5位流水号，预留3位扩展
     *
     * @param value 属性值
     */
    public void setGroupOrderNo(String value) {
        this.groupOrderNo = value;
    }

    private Integer gpId;

    public Integer getGpId() {
        return this.gpId;
    }

    public void setGpId(Integer value) {
        this.gpId = value;
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

    private String mobile;

    /**
     * 获取 收货人手机号。
     */
    public String getMobile() {
        return this.mobile;
    }

    /**
     * 设置 收货人手机号。
     *
     * @param value 属性值
     */
    public void setMobile(String value) {
        this.mobile = value;
    }

    private String name;

    /**
     * 获取 收货人姓名。
     */
    public String getName() {
        return this.name;
    }

    /**
     * 设置 收货人姓名。
     *
     * @param value 属性值
     */
    public void setName(String value) {
        this.name = value;
    }

    private String neighborhood;

    /**
     * 获取 收货地址。
     */
    public String getNeighborhood() {
        return this.neighborhood;
    }

    /**
     * 设置 收货地址。
     *
     * @param value 属性值
     */
    public void setNeighborhood(String value) {
        this.neighborhood = value;
    }

    private Integer inviterId;

    /**
     * 获取 邀请人ID。
     */
    public Integer getInviterId() {
        return this.inviterId;
    }

    /**
     * 设置 邀请人ID。
     *
     * @param value 属性值
     */
    public void setInviterId(Integer value) {
        this.inviterId = value;
    }

    private Integer orderStatus;

    /**
     * 获取 订单状态。
     *
     * <p>
     * 0：新创建<br />
     * 30：已付定金<br />
     * 31：已退定金<br />
     * 50：已付全款<br />
     * 100：已结单
     */
    public Integer getOrderStatus() {
        return this.orderStatus;
    }

    /**
     * 设置 订单状态。
     *
     * <p>
     * 0：新创建<br />
     * 30：已付定金<br />
     * 31：已退定金<br />
     * 50：已付全款<br />
     * 100：已结单
     *
     * @param value 属性值
     */
    public void setOrderStatus(Integer value) {
        this.orderStatus = value;
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

    private String goodsNote;

    /**
     * 获取 商品描述。
     */
    public String getGoodsNote() {
        return this.goodsNote;
    }

    /**
     * 设置 商品描述。
     *
     * @param value 属性值
     */
    public void setGoodsNote(String value) {
        this.goodsNote = value;
    }

    private BigDecimal depositAmount;

    /**
     * 获取 定金金额。
     */
    public BigDecimal getDepositAmount() {
        return this.depositAmount;
    }

    /**
     * 设置 定金金额。
     *
     * @param value 属性值
     */
    public void setDepositAmount(BigDecimal value) {
        this.depositAmount = value;
    }

    private Date depositPayTime;

    /**
     * 获取 定金支付时间。
     */
    public Date getDepositPayTime() {
        return this.depositPayTime;
    }

    /**
     * 设置 定金支付时间。
     *
     * @param value 属性值
     */
    public void setDepositPayTime(Date value) {
        this.depositPayTime = value;
    }

    private String depositReturnUser;

    /**
     * 获取 退单人。
     */
    public String getDepositReturnUser() {
        return this.depositReturnUser;
    }

    /**
     * 设置 退单人。
     *
     * @param value 属性值
     */
    public void setDepositReturnUser(String value) {
        this.depositReturnUser = value;
    }

    private Date depositReturnTime;

    /**
     * 获取 定金退单时间。
     */
    public Date getDepositReturnTime() {
        return this.depositReturnTime;
    }

    /**
     * 设置 定金退单时间。
     *
     * @param value 属性值
     */
    public void setDepositReturnTime(Date value) {
        this.depositReturnTime = value;
    }

    private BigDecimal totalAmount;

    /**
     * 获取 订单总金额。
     */
    public BigDecimal getTotalAmount() {
        return this.totalAmount;
    }

    /**
     * 设置 订单总金额。
     *
     * @param value 属性值
     */
    public void setTotalAmount(BigDecimal value) {
        this.totalAmount = value;
    }

    private Date createTime;

    /**
     * 获取 下单时间。
     */
    public Date getCreateTime() {
        return this.createTime;
    }

    /**
     * 设置 下单时间。
     *
     * @param value 属性值
     */
    public void setCreateTime(Date value) {
        this.createTime = value;
    }

    private String createUser;

    /**
     * 获取 下单人。
     */
    public String getCreateUser() {
        return this.createUser;
    }

    /**
     * 设置 下单人。
     *
     * @param value 属性值
     */
    public void setCreateUser(String value) {
        this.createUser = value;
    }

    private Date updateTime;

    /**
     * 获取 修改时间。
     */
    public Date getUpdateTime() {
        return this.updateTime;
    }

    /**
     * 设置 修改时间。
     *
     * @param value 属性值
     */
    public void setUpdateTime(Date value) {
        this.updateTime = value;
    }

    private String updateUser;

    /**
     * 获取 修改人。
     */
    public String getUpdateUser() {
        return this.updateUser;
    }

    /**
     * 设置 修改人。
     *
     * @param value 属性值
     */
    public void setUpdateUser(String value) {
        this.updateUser = value;
    }

    private String note;

    /**
     * 获取 备注。
     */
    public String getNote() {
        return this.note;
    }

    /**
     * 设置 备注。
     *
     * @param value 属性值
     */
    public void setNote(String value) {
        this.note = value;
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

    private Date registedTime;

    /**
     * 获取 会员注册时间
     * @return
     */
    public Date getRegistedTime() {
        return registedTime;
    }

    /**
     * 设置 会员注册时间
     * @param registedTime
     */
    public void setRegistedTime(Date registedTime) {
        this.registedTime = registedTime;
    }

}