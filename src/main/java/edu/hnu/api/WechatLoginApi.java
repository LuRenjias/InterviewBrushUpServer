package edu.hnu.api;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

@HttpExchange("/sns")
public interface WechatLoginApi {
    @GetExchange("/jscode2session")
    String wechatLogin(String appid, String secret, String js_code, String grant_type);
}
