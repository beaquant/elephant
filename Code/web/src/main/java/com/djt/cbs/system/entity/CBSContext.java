package com.djt.cbs.system.entity;

public class CBSContext {
    private SysUser currentUser;

    /**
     * 当前登陆用户。
     * @return
     */
    public SysUser getCurrentUser() {
        return this.currentUser;
    }

    /**
     * 当前登陆用户。
     * @param value
     */
    public void setCurrentUser(SysUser value) {
        this.currentUser = value;
    }

    private long loginTime;

    /**
     * 登陆时间。
     * @return
     */
    public long getLoginTime() {
        return this.loginTime;
    }

    /**
     * 登陆时间。
     * @param value
     */
    public void setLoginTime(long value) {
        this.loginTime = value;
    }

    private String sessionId;

    public String getSessionId() {
        return this.sessionId;
    }

    public void setSessionId(String value) {
        this.sessionId = value;
    }

}