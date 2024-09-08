package com.systex.demo.configuration;

import com.theokanning.openai.service.OpenAiService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class openAiConfiguration {

    // 使用 @Value 直接注入配置文件中的值
    @Value("${openai.api.key}")
    private String openAiKey;

    @Bean
    public OpenAiService openAiService() {
        String token = openAiKey;
        if (token == null || token.isEmpty()) {
            throw new IllegalStateException("API token not found in environment variables.");
        }
        return new OpenAiService(token, Duration.ofSeconds(30));
    }

}
