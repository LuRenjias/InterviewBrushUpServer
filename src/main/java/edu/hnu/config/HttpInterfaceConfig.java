package edu.hnu.config;

import edu.hnu.api.WechatLoginApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class HttpInterfaceConfig {
    @Value("${remote.base-url}")
    private String baseUrl;

    @Bean
    WebClient webClient() {
        return WebClient.builder()
            .baseUrl(baseUrl)
            .build();
    }

    @Bean
    WechatLoginApi wechatLoginApi(WebClient webClient) {
        HttpServiceProxyFactory factory =
            HttpServiceProxyFactory
                .builder(WebClientAdapter.forClient(webClient))
                .build();
        return factory.createClient(WechatLoginApi.class);
    }
}