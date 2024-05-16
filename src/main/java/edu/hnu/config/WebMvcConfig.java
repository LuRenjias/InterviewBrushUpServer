package edu.hnu.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 配置resource上传既可以访问
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String avatarPath = System.getProperty("user.dir") + File.separator
                + "src" + File.separator + "main" + File.separator
                + "resources" + File.separator + "static" + File.separator + "avatar" + File.separator;
        String imagePath = System.getProperty("user.dir") + File.separator
                + "src" + File.separator + "main" + File.separator
                + "resources" + File.separator + "static" + File.separator + "image" + File.separator;
        registry.addResourceHandler("/avatar/**").addResourceLocations("file:" + avatarPath);
        registry.addResourceHandler("/image/**").addResourceLocations("file:" + imagePath);
    }


}

