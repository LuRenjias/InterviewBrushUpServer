package edu.hnu.filter;

import com.alibaba.fastjson.JSONObject;
import edu.hnu.utils.JwtUtils;
import edu.hnu.utils.Result;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.io.IOException;

@WebFilter("/*")
@Slf4j
public class LoginCheckFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        resp.setHeader("Access-Control-Allow-Origin", "*"); // 允许所有域访问，生产环境中应该更加严格
        resp.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
        resp.setHeader("Access-Control-Allow-Headers", "*");
        //1.获取请求url
        String url = req.getRequestURL().toString();
        log.info("url:{}", url);
        //2.判断请求是否包含有login、register或code，如果包含，放行
        if (url.contains("Login") || url.contains("register") || url.contains("sendCaptcha")) {
            log.info("登录、注册或发送验证码操作，放行");
            filterChain.doFilter(request, response);
            return;
        }
        //3.获取请求头中的令牌(token)
        String jwt = req.getHeader("token");
        //4.判断令牌是否存在
        if (!StringUtils.hasLength(jwt)) {
            log.info("请求头token为空");
            Result error = Result.error("NOT_LOGIN");
            String s = JSONObject.toJSONString(error); // 手动转换，对象->json
            resp.getWriter().write(s);
            return;
        }
        //5.解析token
        try {
            JwtUtils.parseJwt(jwt);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("解析令牌失败，返回未登录错误信息");
            Result error = Result.error("NOT_LOGIN");
            String s = JSONObject.toJSONString(error); // 手动转换，对象->json
            resp.getWriter().write(s);
            return;
        }
        //6.放行
        log.info("令牌合法");
        filterChain.doFilter(request, response);
    }

}
