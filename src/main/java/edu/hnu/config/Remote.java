package edu.hnu.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "remote")
public class Remote {
    private String baseUrl;
    private String appid;
    private String secret;
}
