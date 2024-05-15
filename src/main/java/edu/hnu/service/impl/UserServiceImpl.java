package edu.hnu.service.impl;

import com.alibaba.fastjson.JSONObject;
import edu.hnu.api.WechatLoginApi;
import edu.hnu.dao.UserDao;
import edu.hnu.entity.User;
import edu.hnu.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;


@Service("userService")
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;

    @Resource
    private WechatLoginApi wechatLoginApi;


    @Value("#{remote.appid}")
    private String appid;

    @Value("#{remote.secret}")
    private String secret;

    @Override
    public String getOpenId(String code) {
        String json = wechatLoginApi.wechatLogin(appid, secret, code, "authorization_code");
        JSONObject jsonObject = JSONObject.parseObject(json);
        return jsonObject.getString("openid");
    }

    @Override
    public User getUserByOpenId(String openId) {
        User query = userDao.queryByOpenId(openId);

        if (query != null) {
            // 修改登陆时间、ip等
            return query;
        }

        User user = new User();
        // 构建新用户
        userDao.insert(user);

        return userDao.queryByOpenId(openId);

    }
}
