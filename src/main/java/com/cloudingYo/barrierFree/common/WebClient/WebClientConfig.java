package com.cloudingYo.barrierFree.common.WebClient;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {
    @Bean
    public WebClient webClient(WebClient.Builder webClientBuilder) {
        return webClientBuilder
                .baseUrl("ingress-ngi-ingress-ngin-16d3b-100186920-4cd64223bbc6.kr.lb.naverncp.com/dbupdate")  // 기본 URL 설정
                .defaultHeader("Content-Type", "application/json") // 공통 헤더 추가
                .build();
    }
}
