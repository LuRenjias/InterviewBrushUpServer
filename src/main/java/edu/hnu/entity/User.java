package edu.hnu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.io.Serializable;

/**
 * (User)实体类
 *
 * @author lx
 * @since 2024-05-14 10:13:32
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
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
    private String gender;
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


}

