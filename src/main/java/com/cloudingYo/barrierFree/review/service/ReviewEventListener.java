package com.cloudingYo.barrierFree.review.service;

import com.cloudingYo.barrierFree.review.document.Review;
import com.cloudingYo.barrierFree.review.dto.req.ReviewSavedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@Slf4j
@RequiredArgsConstructor
public class ReviewEventListener {
    private final WebClient webClient;
    private final RetryTemplate retryTemplate;

    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void triggerRecommandSystem(ReviewSavedEvent event){
        Review review = event.getReview();
        log.info("트랜잭션 커밋 후 ML 서버에 트리거 전송 시작... Review ID: {}", review.getId());

        try {
            retryTemplate.execute(context -> {
                webClient.post()
                        .uri("/update_recommend")
                        .bodyValue(review)
                        .retrieve()
                        .bodyToMono(String.class)
                        .doOnSuccess(response -> log.info("ML 서버 응답: {}", response))
                        .doOnError(error -> log.error("ML 서버 트리거 실패 (재시도 중)... {}", error.getMessage()))
                        .block();

                return null;
            });
        } catch (Exception e) {
            log.error("ML 서버 트리거 재시도 실패, 최종적으로 요청을 포기합니다. Review ID: {}", review.getId());
        }
    }
}

