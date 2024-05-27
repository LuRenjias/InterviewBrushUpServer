package edu.hnu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * (Favorite)实体类
 *
 * @author lx
 * @since 2024-05-14 10:12:58
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Favorite {
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
    private LocalDateTime favoriteTime;


}

