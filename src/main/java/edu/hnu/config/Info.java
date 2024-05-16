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
    private int nicknameLength;
    private String defaultAvatarUrl;
    private String avatarBaseUrl;
}
