package edu.hnu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * (Comment)实体类
 *
 * @author lx
 * @since 2024-05-14 10:12:46
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    /**
     * 评论_id
     */
    private Integer id;
    /**
     * 评论_用户的_id
     */
    private Integer userId;
    /**
     * 被评论文章或八股的_id
     */
    private Integer categoryId;
    /**
     * 评论_内容
     */
    private String commentContent;
    /**
     * 评论_时间
     */
    private LocalDateTime commentTime;
    /**
     * 父评论的_id
     */
    private Integer parentCommentId;
    /**
     * 评论_类别
     */
    private Integer category;

    private Integer status; // 评论状态 1表示正常 -1表示已删除

    private List<Comment> replies; // 回复（子评论）
}

