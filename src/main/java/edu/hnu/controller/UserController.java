package edu.hnu.controller;

import edu.hnu.entity.User;
import edu.hnu.service.UserService;
import edu.hnu.utils.JwtUtils;
import edu.hnu.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

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
    public Result weChatLogin(String code) {
        log.info("weChatLogin: 微信小程序登录");

        String openId = userService.getOpenId(code);

        if (openId == null) {
            return Result.error();
        }

        User user = userService.getUserByOpenId(openId);

        String token = JwtUtils.getToken(user);

        return Result.success(token);

    }

}

