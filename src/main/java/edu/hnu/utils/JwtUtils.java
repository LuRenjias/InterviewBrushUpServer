package edu.hnu.utils;

import edu.hnu.entity.Admin;
import edu.hnu.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * (JwtUtils)Jwt工具类.
 *
 * @author 王明扬
 * @since 2023 -11-07 10:14:04
 */
public class JwtUtils {

    private static final String signKey = "FinancialAnalysisService";
    private static final Long expire = 432000000L; // 120小时后过期--5天

    /**
     * 生成JWT令牌.
     *
     * @param claims JWT第二部分负载 payload 中存储的内容
     * @return 令牌 string
     */
    public static String generateJwt(Map<String, Object> claims) {
        return Jwts.builder()
                .addClaims(claims)
                .signWith(SignatureAlgorithm.HS256, signKey)
                .setExpiration(new Date(System.currentTimeMillis() + expire))
                .compact();
    }

    /**
     * 解析JWT令牌.
     *
     * @param jwt JWT令牌
     * @return JWT第二部分负载 payload 中存储的内容
     */
    public static Claims parseJwt(String jwt) {
        return Jwts.parser()
                .setSigningKey(signKey)
                .parseClaimsJws(jwt)
                .getBody();
    }

    /**
     * 获取用户编号userId.
     *
     * @param token the token
     * @return the user id
     * @author 王明扬
     */
    public static Integer getUserId(String token) {
        return (Integer) JwtUtils.parseJwt(token).get("userId");
    }

    /**
     * 根据用户ID生成JWT令牌.
     */
    public static String getToken(User user) {
        Map<String, Object> claims = new HashMap<>();
        Integer id = user.getId();
        claims.put("userId", id);
        return generateJwt(claims);
    }

    /**
     * 根据管理员手机号生成JWT令牌.
     */
    public static String getAdminToken(Admin admin) {
        Map<String, Object> claims = new HashMap<>();
        String phoneNumber = admin.getPhoneNumber();
        claims.put("phoneNumber", phoneNumber);
        return generateJwt(claims);
    }
}
