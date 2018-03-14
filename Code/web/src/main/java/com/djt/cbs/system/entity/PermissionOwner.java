package com.djt.cbs.system.entity;

import java.io.Serializable;

public class PermissionOwner implements Serializable {
    /**
     *Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -1046953621061626287L;

    private int               type;

    /**
     * 权限所有者类型。
     * 参考{@link SysPermission#OWNER_ROLE}、{@link SysPermission#OWNER_USER}。
     * @return
     */
    public int getType() {
        return this.type;
    }

    /**
     * 权限所有者类型。
     * 参考{@link SysPermission#OWNER_ROLE}、{@link SysPermission#OWNER_USER}。
     * @param value
     */
    public void setType(int value) {
        this.type = value;
    }

    private int id;

    /**
     * 权限所有者ID。
     * @return
     */
    public int getId() {
        return this.id;
    }

    /**
     * 权限所有者ID。
     * @param value
     */
    public void setId(int value) {
        this.id = value;
    }

    public String name;

    /**
     * 权限所有者名称。
     * @return
     */
    public String getName() {
        return this.name;
    }

    /**
     * 权限所有者名称。
     * @param value
     */
    public void setName(String value) {
        this.name = value;
    }

    public int status;

    /**
     * 权限所有者状态。
     * @return
     */
    public int getStatus() {
        return this.status;
    }

    /**
     * 权限所有者状态。
     * @param value
     */
    public void setStatus(int value) {
        this.status = value;
    }
}