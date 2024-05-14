package edu.hnu.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (Follow)实体类
 *
 * @author lx
 * @since 2024-05-14 10:13:10
 */
public class Follow implements Serializable {
    private static final long serialVersionUID = 812122546906542762L;
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


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getFollowerUserId() {
        return followerUserId;
    }

    public void setFollowerUserId(Integer followerUserId) {
        this.followerUserId = followerUserId;
    }

    public Date getFollowTime() {
        return followTime;
    }

    public void setFollowTime(Date followTime) {
        this.followTime = followTime;
    }

}

