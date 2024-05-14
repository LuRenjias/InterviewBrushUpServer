package edu.hnu.entity;

import java.io.Serializable;

/**
 * (Admin)实体类
 *
 * @author lx
 * @since 2024-05-14 10:10:05
 */
public class Admin implements Serializable {
    private static final long serialVersionUID = -62547648442080546L;
/**
     * 管理员_id
     */
    private Integer id;
/**
     * 管理员_手机号
     */
    private String phoneNumber;
/**
     * 管理员_密码
     */
    private String password;
/**
     * 管理员_昵称
     */
    private String nickname;
/**
     * 管理员_头像地址
     */
    private String avatarUrl;
/**
     * 管理员_性别
     */
    private String[] gender;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

}

