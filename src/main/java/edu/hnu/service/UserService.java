package edu.hnu.service;


import edu.hnu.entity.User;

public interface UserService {

    /**
     * 调用微信服务端，获取openid（微信用户的唯一标识）.
     */
    String getOpenId(String code);

    /**
     * 根据openid获取/新增用户信息.
     */
    User getUserByOpenId(String openId);
}
