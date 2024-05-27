package edu.hnu.controller;

import com.github.pagehelper.Page;
import edu.hnu.dto.CommentDTO;
import edu.hnu.entity.Comment;
import edu.hnu.service.CommentService;
import edu.hnu.utils.JwtUtils;
import edu.hnu.utils.Result;
import edu.hnu.utils.StatusCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Comment)表控制层
 *
 * @author lx
 * @since 2024-05-14 10:12:46
 */
@RestController
@RequestMapping("comment")
@Slf4j
public class CommentController {
    /**
     * 服务对象
     */
    @Resource
    private CommentService commentService;

    /**
     * 添加评论.
     */
    @PostMapping("addComment")
    public Result addComment(Comment comment, @RequestHeader String token) {
        log.info("addComment: 添加评论");

        comment.setUserId(JwtUtils.getUserId(token));
        int i = commentService.addComment(comment);

        return switch (i) {
            case 0 -> Result.error(StatusCode.PARENT_COMMENT_NOT_EXIST);
            case 1 -> Result.success();
            default -> null;
        };
    }

    /**
     * 查看评论.
     */
    @GetMapping("getComment")
    public Result getComment(Integer category, Integer categoryId) {
        log.info("getComment: 查看评论");

        List<CommentDTO> comment = commentService.getComment(category, categoryId);

        return Result.success(comment);

    }

    /**
     * 删除评论(仅修改状态).
     */
    @PutMapping("deleteComment")
    public Result deleteComment(Integer commentId, @RequestHeader String token) {
        log.info("deleteComment: 删除评论(仅修改状态)");

        // 评论状态 1表示正常 -1表示已删除
        int i = commentService.updateCommentStatus(commentId, -1, JwtUtils.getUserId(token));

        return switch (i) {
            case 0 -> Result.error(StatusCode.ILLEGAL_DELETION);
            case 1 -> Result.success();
            default -> null;
        };
    }

}

