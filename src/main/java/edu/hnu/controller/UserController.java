package edu.hnu.controller;

import edu.hnu.entity.User;
import edu.hnu.service.UserService;
import edu.hnu.utils.IpUtil;
import edu.hnu.utils.JwtUtils;
import edu.hnu.utils.Result;
import edu.hnu.utils.StatusCode;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;

@RestController
@RequestMapping("user")
@Slf4j
public class UserController {
    /**
     * 服务对象
     */
    @Resource
    private UserService userService;

    /**
     * 微信小程序登录.
     */
    @GetMapping("weChatLogin")
    public Result weChatLogin(String code, HttpServletRequest request) {
        log.info("weChatLogin: 微信小程序登录");

        String openId = userService.getOpenId(code);

        if (openId == null) {
            return Result.error(StatusCode.OPENID_ERROR);
        }

        String ipAddress = IpUtil.getIpAddr(request);
        User user = userService.getUserByOpenId(openId, ipAddress);

        String token = JwtUtils.getToken(user);

        return Result.success(token);

    }

    /**
     * 编辑资料.
     */
    @PutMapping("editProfile")
    public Result editProfile(User user, @RequestHeader String token) {
        log.info("editProfile: 编辑资料");

        user.setId(JwtUtils.getUserId(token));

        userService.editProfile(user);

        return Result.success();
    }

    /**
     * 上传头像.
     */
    @PostMapping("uploadAvatar")
    public Result uploadAvatar(MultipartFile file, @RequestHeader String token) throws IOException {
        log.info("uploadAvatar: 上传头像");

        int i = userService.uploadAvatar(file, JwtUtils.getUserId(token));

        return switch (i) {
            case 0 -> Result.error(StatusCode.FILE_IS_EMPTY);
            case 1 -> Result.error(StatusCode.ILLEGAL_FORMAT);
            case 2 -> Result.success();
            default -> null;
        };

    }

}

