package com.example.iotfreshtransportserver.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**") // 设置允许跨域的接口路径
                .allowedOrigins("http://localhost:8080") // 设置允许跨域请求的源，可以设置为特定域名或 "*" 允许任何源
                .allowedMethods("GET", "POST", "PUT", "DELETE") // 设置允许的HTTP方法
                .allowedHeaders("*"); // 设置允许的请求头，默认允许所有
    }
}
