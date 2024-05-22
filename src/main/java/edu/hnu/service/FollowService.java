package edu.hnu.service;

import com.github.pagehelper.Page;
import edu.hnu.dto.UserAbbreviationsDTO;
import edu.hnu.entity.Follow;

import java.util.List;


/**
 * (Follow)表服务接口
 *
 * @author lx
 * @since 2024-05-14 10:13:10
 */
public interface FollowService {

    /**
     * 添加关注.
     */
    int addFollow(Integer userId, Integer followerUserId);

    /**
     * 移除关注.
     */
    int removeFollow(Integer userId, Integer followerUserId);

    /**
     * 关注列表.
     */
    List<UserAbbreviationsDTO> followList(Integer userId);

    /**
     * 粉丝列表.
     */
    List<UserAbbreviationsDTO> fanList(Integer userId);
}
