package edu.hnu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * (Admin)实体类
 *
 * @author lx
 * @since 2024-05-14 10:10:05
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Admin {
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
    private String gender;

}

