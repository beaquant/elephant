package com.djt.cbs.entity;

import java.io.Serializable;

/**
 * 团购活动明细。
 *
 * <p>Table: <strong>group_purchase_item</strong>
 * <p><table class="er-mapping" cellspacing=0 cellpadding=0 style="border:solid 1 #666;padding:3px;">
 *   <tr style="background-color:#ddd;Text-align:Left;">
 *     <th nowrap>属性名</th><th nowrap>属性类型</th><th nowrap>字段名</th><th nowrap>字段类型</th><th nowrap>说明</th>
 *   </tr>
 *   <tr><td>id</td><td>{@link Integer}</td><td>id</td><td>int</td><td>&nbsp;</td></tr>
 *   <tr><td>gpId</td><td>{@link Integer}</td><td>gp_id</td><td>int</td><td>&nbsp;</td></tr>
 *   <tr><td>productId</td><td>{@link Integer}</td><td>product_id</td><td>int</td><td>商品ID</td></tr>
 * </table>
 *
 * @author 周智勇
 * @date 2015-3-19
 * @email 36316500@qq.com
 */
public class GroupPurchaseItem implements Serializable {
    /** Comment for <code>serialVersionUID</code> */
    private static final long serialVersionUID = -1488148492323106395L;

    private Integer           id;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer value) {
        this.id = value;
    }

    private Integer gpId;

    public Integer getGpId() {
        return this.gpId;
    }

    public void setGpId(Integer value) {
        this.gpId = value;
    }

    private Integer productId;

    /**
     * 获取 商品ID。
     */
    public Integer getProductId() {
        return this.productId;
    }

    /**
     * 设置 商品ID。
     *
     * @param value 属性值
     */
    public void setProductId(Integer value) {
        this.productId = value;
    }

}