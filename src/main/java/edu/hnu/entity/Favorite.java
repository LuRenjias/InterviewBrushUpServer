package edu.hnu.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (Favorite)实体类
 *
 * @author lx
 * @since 2024-05-14 10:12:58
 */
public class Favorite implements Serializable {
    private static final long serialVersionUID = 856103806714175181L;
/**
     * 收藏_id
     */
    private Integer id;
/**
     * 收藏_用户的_id
     */
    private Integer userId;
/**
     * 收藏_内容所属分组的_id
     */
    private Integer collectionId;
/**
     * 被收藏_内容的_id
     */
    private Integer favoriteContentId;
/**
     * 收藏_时间
     */
    private Date favoriteTime;


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

    public Integer getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(Integer collectionId) {
        this.collectionId = collectionId;
    }

    public Integer getFavoriteContentId() {
        return favoriteContentId;
    }

    public void setFavoriteContentId(Integer favoriteContentId) {
        this.favoriteContentId = favoriteContentId;
    }

    public Date getFavoriteTime() {
        return favoriteTime;
    }

    public void setFavoriteTime(Date favoriteTime) {
        this.favoriteTime = favoriteTime;
    }

}

