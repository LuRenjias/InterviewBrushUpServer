package edu.hnu;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
// 指定 mapper 接口所在的包，以及 mapper 接口使用的注解。
@MapperScan(basePackages = "edu.hnu.mapper", annotationClass = Mapper.class)
@ServletComponentScan // 使过滤器生效
public class InterviewBrushUpServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(InterviewBrushUpServerApplication.class, args);
    }

}
