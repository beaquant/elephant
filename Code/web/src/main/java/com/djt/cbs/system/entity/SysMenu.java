package com.djt.cbs.system.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 系统功能菜单。
 *
 * <p>Table: <strong>sys_menu</strong>
 * <p><table class="er-mapping" cellspacing=0 cellpadding=0 style="border:solid 1 #666;padding:3px;">
 *   <tr style="background-color:#ddd;Text-align:Left;">
 *     <th nowrap>属性名</th><th nowrap>属性类型</th><th nowrap>字段名</th><th nowrap>字段类型</th><th nowrap>说明</th>
 *   </tr>
 *   <tr><td>menuId</td><td>{@link Integer}</td><td>menu_id</td><td>int</td><td>菜单ID。</td></tr>
 *   <tr><td>parentId</td><td>{@link Integer}</td><td>parent_id</td><td>int</td><td>父级菜单ID。<br />目前系统只支持2级菜单。</td></tr>
 *   <tr><td>menuName</td><td>{@link String}</td><td>menu_name</td><td>varchar</td><td>菜单名称。</td></tr>
 *   <tr><td>status</td><td>{@link Integer}</td><td>status</td><td>int</td><td>状态。<br />-1逻辑删除，0禁用，1启用</td></tr>
 *   <tr><td>orderBy</td><td>{@link Integer}</td><td>order_by</td><td>int</td><td>排列顺序，值大的排在前面。</td></tr>
 *   <tr><td>menuItemType</td><td>{@link String}</td><td>menu_item_type</td><td>varchar</td><td>功能菜单类型。<br />空字符串：非功能菜单。<br />mdl：菜单模块。<br />app：ExtJS的功能菜单。<br />url：直接url方式指定的功能菜单。</td></tr>
 *   <tr><td>menuItemData</td><td>{@link String}</td><td>menu_item_data</td><td>varchar</td><td>功能菜单的目标地址。<br />功能菜单类型为app时，该字段存放ExtJS App名称；为url时该字段存放目标url地址。</td></tr>
 *   <tr><td>createUser</td><td>{@link String}</td><td>create_user</td><td>varchar</td><td>创建者。</td></tr>
 *   <tr><td>createTime</td><td>{@link Date}</td><td>create_time</td><td>datetime</td><td>创建时间。</td></tr>
 *   <tr><td>updateUser</td><td>{@link String}</td><td>update_user</td><td>varchar</td><td>最后更新人。</td></tr>
 *   <tr><td>updateTime</td><td>{@link Date}</td><td>update_time</td><td>timestamp/date</td><td>最后更新时间。</td></tr>
 * </table>
 *
 */
public class SysMenu implements Serializable {
    /**
     *Comment for <code>serialVersionUID</code>
     */
    private static final long  serialVersionUID = 7025594546348330406L;

    /**
     * 菜单模块。
     */
    public final static String MENU_MODULE      = "mdl";
    /**
     * EXTJS APP开发的菜单功能。
     */
    public final static String MENU_ITEM_APP = "app";
    /**
     * 非EXTJS APP开发的菜单功能，例如外部系统功能，或者CBS内部采用传统web开发方式开发的菜单功能。
     */
    public final static String MENU_ITEM_URL    = "url";

    private Integer            menuId;

    /**
     * 获取 菜单ID。
     */
    public Integer getMenuId() {
        return this.menuId;
    }

    /**
     * 设置 菜单ID。
     *
     * @param value 属性值
     */
    public void setMenuId(Integer value) {
        this.menuId = value;
    }

    private Integer parentId;

    /**
     * 获取 父级菜单ID。
     *
     * <p>
     * 目前系统只支持2级菜单。
     */
    public Integer getParentId() {
        return this.parentId;
    }

    /**
     * 设置 父级菜单ID。
     *
     * <p>
     * 目前系统只支持2级菜单。
     *
     * @param value 属性值
     */
    public void setParentId(Integer value) {
        this.parentId = value;
    }

    private String menuName = "";

    /**
     * 获取 菜单名称。
     */
    public String getMenuName() {
        return this.menuName;
    }

    /**
     * 设置 菜单名称。
     *
     * @param value 属性值
     */
    public void setMenuName(String value) {
        this.menuName = value;
    }

    private Integer status = 0;

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

    private Integer orderBy = 0;

    /**
     * 获取 排列顺序，值大的排在前面。
     */
    public Integer getOrderBy() {
        return this.orderBy;
    }

    /**
     * 设置 排列顺序，值大的排在前面。
     *
     * @param value 属性值
     */
    public void setOrderBy(Integer value) {
        this.orderBy = value;
    }

    private String menuItemType = "url";

    /**
     * 获取 功能菜单类型。
     *
     * <p>
     * 空字符串：非功能菜单。<br />
     * mdl：菜单模块。<br />
     * app：ExtJS的功能菜单。<br />
     * url：直接url方式指定的功能菜单。
     */
    public String getMenuItemType() {
        return this.menuItemType;
    }

    /**
     * 设置 功能菜单类型。
     *
     * <p>
     * 空字符串：非功能菜单。<br />
     * mdl：菜单模块。<br />
     * app：ExtJS的功能菜单。<br />
     * url：直接url方式指定的功能菜单。
     *
     * @param value 属性值
     */
    public void setMenuItemType(String value) {
        this.menuItemType = value;
    }

    private String menuItemData;

    /**
     * 获取 功能菜单的目标地址。
     *
     * <p>
     * 功能菜单类型为app时，该字段存放ExtJS App名称；为url时该字段存放目标url地址。
     */
    public String getMenuItemData() {
        return this.menuItemData;
    }

    /**
     * 设置 功能菜单的目标地址。
     *
     * <p>
     * 功能菜单类型为app时，该字段存放ExtJS App名称；为url时该字段存放目标url地址。
     *
     * @param value 属性值
     */
    public void setMenuItemData(String value) {
        this.menuItemData = value;
    }

    private String createUser = "";

    /**
     * 获取 创建者。
     */
    public String getCreateUser() {
        return this.createUser;
    }

    /**
     * 设置 创建者。
     *
     * @param value 属性值
     */
    public void setCreateUser(String value) {
        this.createUser = value;
    }

    private Date createTime = null;

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

    private String updateUser = "";

    /**
     * 获取 最后更新人。
     */
    public String getUpdateUser() {
        return this.updateUser;
    }

    /**
     * 设置 最后更新人。
     *
     * @param value 属性值
     */
    public void setUpdateUser(String value) {
        this.updateUser = value;
    }

    private Date updateTime;

    /**
     * 获取 最后更新时间。
     */
    public Date getUpdateTime() {
        return this.updateTime;
    }

    /**
     * 设置 最后更新时间。
     *
     * @param value 属性值
     */
    public void setUpdateTime(Date value) {
        this.updateTime = value;
    }

    private List<SysMenu> children;

    public List<SysMenu> getChildren() {
        return this.children;
    }

    public void setChildren(List<SysMenu> value) {
        this.children = value;
    }

    public void addChildren(SysMenu menu) {
        if (this.children == null)
            this.children = new ArrayList<SysMenu>();
        this.children.add(menu);
    }

}