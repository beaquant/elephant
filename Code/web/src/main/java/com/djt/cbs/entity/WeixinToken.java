package com.djt.cbs.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>Table: <strong>weixin_token</strong>
 * <p><table class="er-mapping" cellspacing=0 cellpadding=0 style="border:solid 1 #666;padding:3px;">
 *   <tr style="background-color:#ddd;Text-align:Left;">
 *     <th nowrap>属性名</th><th nowrap>属性类型</th><th nowrap>字段名</th><th nowrap>字段类型</th><th nowrap>说明</th>
 *   </tr>
 *   <tr><td>id</td><td>{@link Integer}</td><td>id</td><td>int</td><td>流水号</td></tr>
 *   <tr><td>accessToken</td><td>{@link String}</td><td>access_token</td><td>varchar</td><td>获取到的凭证</td></tr>
 *   <tr><td>expireTime</td><td>{@link Date}</td><td>expire_time</td><td>datetime</td><td>过期时间</td></tr>
 * </table>
 *
 * @author Benio
 * @date 2014-9-13
 * @email 36316500@qq.com
 */
public class WeixinToken implements Serializable {
    /** Comment for <code>serialVersionUID</code> */
    private static final long serialVersionUID = -6506536723274125816L;

    private Integer           id;

    /**
     * 获取 流水号
     * @return
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置 流水号
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    private String accessToken;

    /**
     * 获取 访问凭证
     * @return
     */
    public String getAccessToken() {
        return accessToken;
    }

    /**
     * 设置 访问凭证
     * @param accessToken
     */
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    private Date expireTime;

    /**
     * 获取 过期时间
     * @return
     */
    public Date getExpireTime() {
        return expireTime;
    }

    /**
     * 设置 过期时间
     * @param expireTime
     */
    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

}
