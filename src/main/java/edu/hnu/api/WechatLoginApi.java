package edu.hnu.api;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

@HttpExchange
public interface WechatLoginApi {
  @GetExchange("/sns/jscode2session")
  String wechatLogin(@RequestParam("appid") String appid,
                     @RequestParam("secret") String secret,
                     @RequestParam("js_code") String jsCode,
                     @RequestParam("grant_type") String grantType);
}