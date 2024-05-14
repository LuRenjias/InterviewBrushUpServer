package edu.hnu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * (Follow)实体类
 *
 * @author lx
 * @since 2024-05-14 10:13:10
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Follow {
    /**
     * 关注_id
     */
    private Integer id;
    /**
     * 被关注用户的_id
     */
    private Integer userId;
    /**
     * 关注者的用户_id
     */
    private Integer followerUserId;
    /**
     * 关注_时间
     */
    private Date followTime;


}

