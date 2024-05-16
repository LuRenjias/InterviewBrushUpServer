package edu.hnu.service;


import edu.hnu.entity.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UserService {

    /**
     * 调用微信服务端，获取openid（微信用户的唯一标识）.
     */
    String getOpenId(String code);

    /**
     * 根据openid获取/新增用户信息.
     */
    User getUserByOpenId(String openId, String ipAddress);

    /**
     * 编辑资料.
     */
    void editProfile(User user);

    /**
     * 上传头像.
     */
    int uploadAvatar(MultipartFile file, int id) throws IOException;
}
