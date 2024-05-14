package edu.hnu.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (Collection)实体类
 *
 * @author lx
 * @since 2024-05-14 10:12:33
 */
public class Collection implements Serializable {
    private static final long serialVersionUID = 206157772440579394L;
/**
     * 分组_id
     */
    private Integer id;
/**
     * 分组_所属用户的_id
     */
    private Integer userId;
/**
     * 分组_名称
     */
    private String collectionName;
/**
     * 分组_创建时间
     */
    private Date createTime;
/**
     * 分组内_内容的数量
     */
    private Integer contentCount;
/**
     * 分组_所属模块
     */
    private Integer module;


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

    public String getCollectionName() {
        return collectionName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getContentCount() {
        return contentCount;
    }

    public void setContentCount(Integer contentCount) {
        this.contentCount = contentCount;
    }

    public Integer getModule() {
        return module;
    }

    public void setModule(Integer module) {
        this.module = module;
    }

}

