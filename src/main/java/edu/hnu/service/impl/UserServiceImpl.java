package edu.hnu.service.impl;

import cn.hutool.core.io.FileTypeUtil;
import com.alibaba.fastjson.JSONObject;
import edu.hnu.api.WechatLoginApi;
import edu.hnu.dao.ArticleDao;
import edu.hnu.dao.FollowDao;
import edu.hnu.dao.UserDao;
import edu.hnu.dto.UserDTO;
import edu.hnu.entity.Article;
import edu.hnu.entity.Follow;
import edu.hnu.entity.User;
import edu.hnu.service.UserService;
import edu.hnu.utils.RandomStringGenerator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;


@Service("userService")
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;

    @Resource
    private ArticleDao articleDao;

    @Resource
    private FollowDao followDao;

    @Resource
    private WechatLoginApi wechatLoginApi;

    @Value("${remote.appid}")
    private String appid;

    @Value("${remote.secret}")
    private String secret;

    @Value("${info.nickname-length}")
    private int nicknameLength;

    @Value("${info.default-avatar-url}")
    private String defaultAvatarUrl;

    @Value("${info.avatar-base-url}")
    private String avatarBaseUrl;

    @Override
    public String getOpenId(String code) {
        String json = wechatLoginApi.wechatLogin(appid, secret, code, "authorization_code");
        JSONObject jsonObject = JSONObject.parseObject(json);
        return jsonObject.getString("openid");
    }

    @Override
    public User getUserByOpenId(String openId, String ipAddress) {
        User query = userDao.queryByOpenId(openId);

        // 已存在
        if (query != null) {
            LocalDateTime lastLoginTime = LocalDateTime.now();
            query.setLastLoginTime(lastLoginTime);
            query.setLastLoginIp(ipAddress);
            userDao.update(query);
            return userDao.queryByOpenId(openId);
        }

        // 新用户，自动注册
        String phoneNumber = "";
        String nickname = RandomStringGenerator.generateRandomString(nicknameLength);
        String avatarUrl = defaultAvatarUrl;
        String gender = "other";
        String userIdentity = "认证用户";
        LocalDateTime registrationTime = LocalDateTime.now();
        LocalDateTime lastLoginTime = LocalDateTime.now();
        String lastLoginDevice = "";
        User user = new User(0, openId, phoneNumber, nickname, avatarUrl, gender,
                0, 0, userIdentity,
                registrationTime, lastLoginTime, ipAddress, lastLoginDevice);
        userDao.insert(user);
        return userDao.queryByOpenId(openId);
    }

    @Override
    public void editProfile(User user) {
        userDao.update(user);
    }

    private final List<String> typeList = Arrays.asList("xbm", "tif", "pjp", "svgz", "jpg", "jpeg", "ico", "tiff", "gif", "svg", "jfif", "webp", "png", "bmp", "pjpeg", "avif");
    private final String avatarBasePath = System.getProperty("user.dir") + File.separator
            + "src" + File.separator + "main" + File.separator + "resources" + File.separator
            + "static" + File.separator + "avatar" + File.separator;

    @Override
    public int uploadAvatar(MultipartFile file, int id) throws IOException {
        // 未上传
        if (file.isEmpty()) {
            return 0;
        }

        // 判断类型
        String fileName = file.getOriginalFilename();
        String fileType = fileName != null ? fileName.substring(fileName.lastIndexOf(".") + 1) : "err";
        System.out.println(fileType.toLowerCase());
        if (!typeList.contains(fileType.toLowerCase())) {
            return 1;
        } else {
            String type = FileTypeUtil.getType(file.getInputStream());
            System.out.println(type);
            if (!typeList.contains(type)) {
                return 1;
            }
        }

        // 删除旧头像
        User query = userDao.queryById(id);
        String avatarUrl = query.getAvatarUrl();
        String avatarName = avatarUrl.substring(avatarUrl.lastIndexOf("/") + 1);
        String defaultAvatarName = defaultAvatarUrl.substring(defaultAvatarUrl.lastIndexOf("/") + 1);
        if (!avatarName.equals(defaultAvatarName)) {
            String deletePath = avatarBasePath + File.separator + avatarName;
            File deleteFile = new File(deletePath);
            deleteFile.delete();
        }

        // 使用UUID表示文件名
        String uuName = UUID.randomUUID().toString();
        // 设置文件访问地址
        avatarUrl = avatarBaseUrl + uuName + "." + fileType;
        // 设置文件保存位置
        String savePath = avatarBasePath + uuName + "." + fileType;

        File saveFile = new File(savePath);
        if (!saveFile.exists()) saveFile.mkdirs();

        // 保存文件
        file.transferTo(saveFile);

        User user = new User();
        user.setId(id);
        user.setAvatarUrl(avatarUrl);
        userDao.update(user);
        return 2;
    }

    @Override
    public UserDTO userInfo(int userId, Integer loginUserId) {
        User query = userDao.queryById(userId);

        if (query == null) {
            return null;
        }

        Article article = new Article();
        article.setUserId(userId);
        long articleCount = articleDao.count(article);

        Follow follow = followDao.queryStatus(userId, loginUserId);
        boolean isFollow = follow != null;

        return new UserDTO(query.getId(), query.getNickname(),
                query.getAvatarUrl(), query.getGender(),
                query.getFollowingCount(), query.getFollowersCount(),
                query.getUserIdentity(), articleCount, isFollow);
    }
}
