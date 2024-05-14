package edu.hnu.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (User)实体类
 *
 * @author lx
 * @since 2024-05-14 10:13:32
 */
public class User implements Serializable {
    private static final long serialVersionUID = -39760317383302500L;
/**
     * 用户_id
     */
    private Integer id;
/**
     * 用户的_openid
     */
    private String openid;
/**
     * 用户_手机号
     */
    private String phoneNumber;
/**
     * 用户_昵称
     */
    private String nickname;
/**
     * 用户_头像地址
     */
    private String avatarUrl;
/**
     * 用户_性别
     */
    private String[] gender;
/**
     * 用户_关注数量
     */
    private Integer followingCount;
/**
     * 用户_粉丝数量
     */
    private Integer followersCount;
/**
     * 用户_身份
     */
    private String userIdentity;
/**
     * 用户_注册时间
     */
    private Date registrationTime;
/**
     * 用户_上次登录时间
     */
    private Date lastLoginTime;
/**
     * 用户_上次登录IP
     */
    private String lastLoginIp;
/**
     * 用户_上次登录设备
     */
    private String lastLoginDevice;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String[] getGender() {
        return gender;
    }

    public void setGender(String[] gender) {
        this.gender = gender;
    }

    public Integer getFollowingCount() {
        return followingCount;
    }

    public void setFollowingCount(Integer followingCount) {
        this.followingCount = followingCount;
    }

    public Integer getFollowersCount() {
        return followersCount;
    }

    public void setFollowersCount(Integer followersCount) {
        this.followersCount = followersCount;
    }

    public String getUserIdentity() {
        return userIdentity;
    }

    public void setUserIdentity(String userIdentity) {
        this.userIdentity = userIdentity;
    }

    public Date getRegistrationTime() {
        return registrationTime;
    }

    public void setRegistrationTime(Date registrationTime) {
        this.registrationTime = registrationTime;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getLastLoginIp() {
        return lastLoginIp;
    }

    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp;
    }

    public String getLastLoginDevice() {
        return lastLoginDevice;
    }

    public void setLastLoginDevice(String lastLoginDevice) {
        this.lastLoginDevice = lastLoginDevice;
    }

}

