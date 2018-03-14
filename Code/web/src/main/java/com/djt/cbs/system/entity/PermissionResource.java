package com.djt.cbs.system.entity;

import java.io.Serializable;
import java.util.List;

public class PermissionResource implements Serializable {

    /**
     *Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -8005853049514900345L;

    private int               rtype;

    /**
     * 权限资源类型。
     * 参考{@link SysPermission#RESOURCE_ACTION}、{@link SysPermission#RESOURCE_MENU}。
     * @return
     */
    public int getRtype() {
        return this.rtype;
    }

    /**
     * 权限资源类型。
     * 参考{@link SysPermission#RESOURCE_ACTION}、{@link SysPermission#RESOURCE_MENU}。
     * @param value
     */
    public void setRtype(int value) {
        this.rtype = value;
    }

    private String rid;

    /**
     * 权限资源ID。
     * @return
     */
    public String getRid() {
        return this.rid;
    }

    /**
     * 权限资源ID。
     * @param value
     */
    public void setRid(String value) {
        this.rid = value;
    }

    public String name;

    /**
     * 权限资源名称。
     * @return
     */
    public String getName() {
        return this.name;
    }

    /**
     * 权限资源名称。
     * @param value
     */
    public void setName(String value) {
        this.name = value;
    }

    public int status;

    /**
     * 权限资源状态。
     * @return
     */
    public int getStatus() {
        return this.status;
    }

    /**
     * 权限资源状态。
     * @param value
     */
    public void setStatus(int value) {
        this.status = value;
    }

    private Boolean checked = false;

    public Boolean getChecked() {
        return this.checked;
    }

    public void setChecked(Boolean value) {
        this.checked = value;
    }

    private List<PermissionResource> children;

    public List<PermissionResource> getChildren() {
        return this.children;
    }

    public void setChildren(List<PermissionResource> value) {
        this.children = value;
    }
}