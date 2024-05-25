package edu.hnu.service.impl;

import edu.hnu.dto.CommentDTO;
import edu.hnu.entity.Comment;
import edu.hnu.dao.CommentDao;
import edu.hnu.service.CommentService;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * (Comment)表服务实现类
 *
 * @author lx
 * @since 2024-05-14 10:12:46
 */
@Service("commentService")
public class CommentServiceImpl implements CommentService {
    @Resource
    private CommentDao commentDao;


    @Override
    public int addComment(Comment comment) {
        Integer parentCommentId = comment.getParentCommentId();
        Comment query = commentDao.queryById(parentCommentId);
        // 父评论不存在
        if (!parentCommentId.equals(0) && query == null) {
            return 0;
        }

        comment.setCommentTime(LocalDateTime.now());
        comment.setStatus(1); // 评论状态 1表示正常 -1表示已删除
        commentDao.insert(comment);
        return 1;
    }

    @Override
    public List<CommentDTO> getComment(Integer category, Integer categoryId) {
        List<CommentDTO> parentComments = commentDao.queryRootByCategoryIdWithCategory(category, categoryId);
        parentComments.forEach(this::loadReplies);
        return parentComments;
    }

    @Override
    public int updateCommentStatus(Integer commentId, Integer status, Integer userId) {
        Comment query = commentDao.queryById(commentId);
        // 评论不存在
        if (query == null) {
            return 0;
        }
        // 不是自己的评论，无权删除
        if (!query.getUserId().equals(userId)) {
            return 0;
        }

        Comment comment = new Comment();
        comment.setId(commentId);
        comment.setStatus(status);
        commentDao.update(comment);
        return 1;
    }

    private void loadReplies(CommentDTO comment) {
        List<CommentDTO> replies = commentDao.queryByParentComment(comment);
        comment.setReplies(replies);
        replies.forEach(this::loadReplies);
    }
}
