package edu.hnu.controller;

import edu.hnu.dto.ArticleAbbreviationsDTO;
import edu.hnu.dto.ArticleDTO;
import edu.hnu.entity.Article;
import edu.hnu.service.ArticleService;
import edu.hnu.utils.JwtUtils;
import edu.hnu.utils.Result;
import edu.hnu.utils.StatusCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * (Article)表控制层
 *
 * @author lx
 * @since 2024-05-14 10:10:33
 */
@RestController
@RequestMapping("article")
@Slf4j
public class ArticleController {
    /**
     * 服务对象
     */
    @Resource
    private ArticleService articleService;

    /**
     * 发布文章.
     */
    @PostMapping("publish")
    public Result publish(Article article, @RequestHeader String token) {
        log.info("publish: 发布文章");

        int articleId = articleService.publish(article, JwtUtils.getUserId(token));

        Map<String, Integer> result = new HashMap<>();
        result.put("articleId", articleId);
        return Result.success(result);
    }

    /**
     * 删除文章.
     */
    @DeleteMapping("delete")
    public Result delete(Integer articleId, @RequestHeader String token) {
        log.info("delete: 删除文章");

        int i = articleService.delete(articleId, JwtUtils.getUserId(token));

        return switch (i) {
            case 0 -> Result.error(StatusCode.ILLEGAL_DELETION);
            case 1 -> Result.success();
            default -> null;
        };
    }

    /**
     * 我的文章.
     */
    @GetMapping("myList")
    public Result myList(Integer userId) {
        log.info("my: 我的文章");

        List<ArticleAbbreviationsDTO> articleAbbreviationsDTOS = articleService.myList(userId);

        return Result.success(articleAbbreviationsDTOS);
    }

    /**
     * 发现页文章.
     */
    @GetMapping("findList")
    public Result findList() {
        log.info("findList: 发现页文章");

        List<ArticleAbbreviationsDTO> list = articleService.findList();

        return Result.success(list);
    }

    /**
     * 文章详情.
     */
    @GetMapping("detail")
    public Result detail(Integer articleId, @RequestHeader String token) {
        log.info("detail: 文章详情");

        ArticleDTO detail = articleService.detail(articleId, JwtUtils.getUserId(token));

        if (detail == null) {
            return Result.error(StatusCode.ARTICLE_NOT_EXIST);
        }

        return Result.success(detail);
    }

    /**
     * 点赞或取消点赞.
     */
    @PutMapping("like")
    public Result like(Integer articleId, boolean like, @RequestHeader String token) {
        log.info("like: 点赞{}或取消点赞{}", like, !like);

        int i = articleService.like(articleId, like, JwtUtils.getUserId(token));

        return switch (i) {
            case 0 -> Result.error(StatusCode.ARTICLE_NOT_EXIST);
            case 1 -> Result.success();
            case 2 -> Result.error(StatusCode.REPEAT_LIKE);
            default -> null;
        };
    }

    /**
     * 点赞记录.
     */
    @GetMapping("likeRecordOrHistory")
    public Result likeRecordOrHistory(Integer userId, Integer type) {
        log.info("likeRecordOrHistory: 点赞记录或历史记录");

        List<ArticleAbbreviationsDTO> articleAbbreviationsDTOS = articleService.likeRecordOrHistory(userId, type);

        return Result.success(articleAbbreviationsDTOS);
    }
}

