package edu.hnu.service;

import edu.hnu.dto.CommentDTO;
import edu.hnu.entity.Comment;

import java.util.List;


/**
 * (Comment)表服务接口
 *
 * @author lx
 * @since 2024-05-14 10:12:46
 */
public interface CommentService {

    /**
     * 添加评论.
     */
    int addComment(Comment comment);

    /**
     * 查看文章评论.
     */
    List<CommentDTO> getComment(Integer category, Integer categoryId);

    int updateCommentStatus(Integer commentId, Integer status, Integer userId);
}
