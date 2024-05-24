package edu.hnu.service.impl;

import cn.hutool.core.lang.Pair;
import edu.hnu.dao.UserDao;
import edu.hnu.dto.ArticleAbbreviationsDTO;
import edu.hnu.dto.UserAbbreviationsDTO;
import edu.hnu.entity.Follow;
import edu.hnu.dao.FollowDao;
import edu.hnu.entity.User;
import edu.hnu.service.FollowService;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * (Follow)表服务实现类
 *
 * @author lx
 * @since 2024-05-14 10:13:10
 */
@Service("followService")
public class FollowServiceImpl implements FollowService {
    @Resource
    private FollowDao followDao;

    @Resource
    private UserDao userDao;


    @Override
    public int addFollow(Integer userId, Integer followerUserId) {
        Follow query = followDao.queryStatus(userId, followerUserId);
        if (query != null) {
            return 0;
        }
        Follow follow = new Follow(0, userId, followerUserId, LocalDateTime.now());
        followDao.insert(follow);

        // 被关注者的粉丝数+1
        User user = userDao.queryById(userId);
        user.setFollowersCount(user.getFollowersCount() + 1);
        userDao.update(user);

        // 关注者的关注数+1
        User followerUser = userDao.queryById(followerUserId);
        followerUser.setFollowingCount(followerUser.getFollowingCount() + 1);
        userDao.update(followerUser);

        return 1;
    }

    @Override
    public int removeFollow(Integer userId, Integer followerUserId) {
        Follow query = followDao.queryStatus(userId, followerUserId);
        if (query == null) {
            return 0;
        }

        followDao.deleteById(query.getId());

        // 被关注者的粉丝数-1
        User user = userDao.queryById(userId);
        user.setFollowersCount(user.getFollowersCount() - 1);
        userDao.update(user);

        // 关注者的关注数-1
        User followerUser = userDao.queryById(followerUserId);
        followerUser.setFollowingCount(followerUser.getFollowingCount() - 1);
        userDao.update(followerUser);

        return 1;
    }

    @Override
    public List<UserAbbreviationsDTO> followList(Integer userId) {
        List<Follow> follows = followDao.queryByFollowerUserId(userId);
        // 关注的用户的ID列表
        List<Integer> followingIds = follows.stream().map(Follow::getUserId).toList();

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("(");
        for (Integer id : followingIds) {
            stringBuilder.append(id).append(",");
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        stringBuilder.append(")");
        String ids = stringBuilder.toString().equals(")") ? null : stringBuilder.toString();

        List<User> userList = userDao.listIn(ids);
        Map<Integer, User> userMap = new HashMap<>();
        for (User user : userList) {
            userMap.put(user.getId(), user);
        }

        List<UserAbbreviationsDTO> userAbbreviations = new ArrayList<>();
        if (!followingIds.isEmpty()) {
            followingIds.forEach(v -> {
                UserAbbreviationsDTO temp = new UserAbbreviationsDTO(v,
                        userMap.get(v).getNickname(),
                        userMap.get(v).getAvatarUrl(),
                        true);
                userAbbreviations.add(temp);
            });
        }
        return userAbbreviations.isEmpty() ? null : userAbbreviations;
    }

    @Override
    public List<UserAbbreviationsDTO> fanList(Integer userId) {
        List<Follow> follows = followDao.queryByUserId(userId);
        // 粉丝的ID列表
        List<Integer> fanIds = follows.stream().map(Follow::getFollowerUserId).toList();

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("(");
        for (Integer id : fanIds) {
            stringBuilder.append(id).append(",");
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        stringBuilder.append(")");
        String ids = stringBuilder.toString().equals(")") ? null : stringBuilder.toString();

        List<User> userList = userDao.listIn(ids);
        Map<Integer, User> userMap = new HashMap<>();
        for (User user : userList) {
            userMap.put(user.getId(), user);
        }

        List<Follow> followList = followDao.queryByFollowerUserId(userId);
        Map<Pair<Integer, Integer>, Integer> followMap = new HashMap<>();
        for (Follow follow : followList) {
            followMap.put(new Pair<>(follow.getFollowerUserId(), follow.getUserId()), 1);
        }

        List<UserAbbreviationsDTO> userAbbreviations = new ArrayList<>();
        if (!fanIds.isEmpty()) {
            fanIds.forEach(v -> {
                boolean followed = followMap.get(new Pair<>(userId, v)) != null;
                UserAbbreviationsDTO temp = new UserAbbreviationsDTO(v,
                        userMap.get(v).getNickname(),
                        userMap.get(v).getAvatarUrl(),
                        followed);
                userAbbreviations.add(temp);
            });
        }
        return userAbbreviations.isEmpty() ? null : userAbbreviations;
    }
}
