package edu.hnu.dto;

import edu.hnu.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDTO {
    private Integer id; // 评论的id
    private Integer userId; // 评论的用户id
    private Integer category; // 0表示文章 1表示八股
    private Integer categoryId; // 被评论文章或八股的id
    private String commentContent; // 评论的内容
    private LocalDateTime commentTime; // 评论的时间
    private Integer parentCommentId; // 父评论的id
    private Integer status; // 评论状态 1表示正常 -1表示已删除

    private String nickname; // 评论者的昵称
    private String avatarUrl; // 评论者的头像

    private List<CommentDTO> replies; // 回复（子评论）
}

