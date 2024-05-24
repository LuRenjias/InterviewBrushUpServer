package edu.hnu.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
@Data
@Component
@ConfigurationProperties(prefix = "info")
public class Info {
    private int nicknameLength; // 随机生成的昵称的长度
    private String defaultAvatarUrl; // 默认头像的访问地址
    private String avatarBaseUrl; // 头像的基地址
    private String imageBaseUrl; // 图片的基地址
    private Integer contentLength; // 文章缩略信息显示的内容长度
    private Integer recommendCount; // 发现页一次性返回的数据
}
