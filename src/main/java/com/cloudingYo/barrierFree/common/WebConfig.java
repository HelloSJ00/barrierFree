package com.cloudingYo.barrierFree.common;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry){
        WebMvcConfigurer.super.addCorsMappings(registry);
        registry.addMapping("/**") // 특정 경로에 대해 CORS 허용
                .allowedOrigins("*") // 허용할 도메인 (Next.js 도메인)
                .allowedMethods(
                        HttpMethod.GET.name(),
                        HttpMethod.POST.name(),
                        HttpMethod.PUT.name(),
                        HttpMethod.DELETE.name(),
                        HttpMethod.OPTIONS.name()) // 허용할 HTTP 메소드
                .allowedHeaders("*") // 허용할 헤더
                .allowCredentials(true) // 쿠키 등 자격 증명 허용 여부
                .maxAge(3600); // 설정된 CORS의 유효 시간 (초)
    }
}
