package edu.hnu.controller;

import com.github.pagehelper.Page;
import edu.hnu.dto.UserAbbreviationsDTO;
import edu.hnu.entity.Follow;
import edu.hnu.service.FollowService;
import edu.hnu.utils.JwtUtils;
import edu.hnu.utils.Result;
import edu.hnu.utils.StatusCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Follow)表控制层
 *
 * @author lx
 * @since 2024-05-14 10:13:10
 */
@RestController
@RequestMapping("follow")
@Slf4j
public class FollowController {
    /**
     * 服务对象
     */
    @Resource
    private FollowService followService;

    /**
     * 添加关注.
     */
    @PostMapping("addFollow")
    public Result addFollow(Integer userId, Integer followerUserId) {
        log.info("addFollow: 添加关注");

        int i = followService.addFollow(userId, followerUserId);

        return switch (i) {
            case 0 -> Result.error(StatusCode.REPEAT_FOLLOW);
            case 1 -> Result.success();
            default -> null;
        };
    }

    /**
     * 移除关注.
     */
    @DeleteMapping("removeFollow")
    public Result removeFollow(Integer userId, Integer followerUserId) {
        log.info("removeFollow: 移除关注");

        int i = followService.removeFollow(userId, followerUserId);

        return switch (i) {
            case 0 -> Result.error(StatusCode.REPEAT_REMOVE_FOLLOW);
            case 1 -> Result.success();
            default -> null;
        };
    }

    /**
     * 关注列表.
     */
    @GetMapping("followList")
    public Result followList(Integer userId) {
        log.info("followList: 关注列表");

        List<UserAbbreviationsDTO> userAbbreviationsDTOS = followService.followList(userId);

        return Result.success(userAbbreviationsDTOS);
    }

    /**
     * 粉丝列表.
     */
    @GetMapping("fanList")
    public Result fanList(Integer userId) {
        log.info("fanList: 粉丝列表");

        List<UserAbbreviationsDTO> userAbbreviationsDTOS = followService.fanList(userId);

        return Result.success(userAbbreviationsDTOS);
    }

}

