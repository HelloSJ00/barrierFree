package com.cloudingYo.barrierFree.review.service;

import com.cloudingYo.barrierFree.place.entity.Place;
import com.cloudingYo.barrierFree.place.repository.PlaceRepository;
import com.cloudingYo.barrierFree.review.document.Review;
import com.cloudingYo.barrierFree.review.dto.req.ReviewDTO;
import com.cloudingYo.barrierFree.review.dto.req.ReviewSavedEvent;
import com.cloudingYo.barrierFree.review.repository.ReviewRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;
import org.springframework.web.reactive.function.client.WebClient;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final PlaceRepository placeRepository;
    private final ApplicationEventPublisher eventPublisher;
    private final WebClient webClient;

    @Override
    public Review createReview(ReviewDTO reviewDTO, HttpSession session) {
        // MongoDB에 저장 (트랜잭션 적용됨)
        Review review = reviewRepository.saveReview(Review.builder()
                .placeKey(reviewDTO.getPlaceKey())
                .userId(reviewDTO.getUserId())
                .content(reviewDTO.getContent())
                .build());

        // ReviewServiceImpl에서 리뷰 저장 후 이벤트 발행
        eventPublisher.publishEvent(new ReviewSavedEvent(review));
        return review; // 트랜잭션 종료 시점
    }

    @Override
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void triggerRecommandSystem(ReviewSavedEvent event){
        Review review = event.getReview();
        log.info("트랜잭션 커밋 후 ML 서버에 트리거 전송 시작... Review ID: {}", review.getId());

        webClient.post()
                .uri("http://ml-server/retrain")
                .bodyValue(review)
                .retrieve()
                .bodyToMono(String.class)
                .doOnSuccess(response -> log.info("ML 서버 응답: {}", response))
                .doOnError(error -> log.error("ML 서버 트리거 실패: {}", error.getMessage()))
                .subscribe(); // 비동기 실행
    }

    @Override
    public Review deleteReview(Long placeKey, HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        return reviewRepository.deleteByPlaceKeyAndUserId(placeKey, userId);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ReviewDTO> getReviewsByUserId(HttpSession session, int page) {
        Long userId = (Long) session.getAttribute("userId");
        Pageable pageable = PageRequest.of(page, 5);
        Page<Review> reviewPage = reviewRepository.findByUserIdOrderByCreatedAtDesc(userId, pageable);

        return reviewPage.map(review -> {
            Place place = placeRepository.findByPlaceKey(review.getPlaceKey());
            String placeName = place != null ? place.getPlacename() : "Unknown Place";

            return ReviewDTO.builder()
                    .placeKey(review.getPlaceKey())
                    .userId(review.getUserId())
                    .username(review.getUsername())
                    .rating(review.getRating())
                    .content(review.getContent())
                    .createdAt(review.getCreatedAt())
                    .isMine(true)
                    .placename(placeName)
                    .build();
        });
    }

    @Override
    public Page<ReviewDTO> getReviewsByPlaceKey(Long placeKey, int page,HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        Pageable pageable = PageRequest.of(page, 5, Sort.by(Sort.Direction.DESC, "createdAt"));
        Page<Review> reviewPage = reviewRepository.findByPlaceKeyOrderByCreatedAtDesc(placeKey, pageable);

        return reviewPage.map(review -> {
            Place place = placeRepository.findByPlaceKey(review.getPlaceKey());
            String placeName = place != null ? place.getPlacename() : "Unknown Place";

            return ReviewDTO.builder()
                    .placeKey(review.getPlaceKey())
                    .userId(review.getUserId())
                    .username(review.getUsername())
                    .rating(review.getRating())
                    .content(review.getContent())
                    .createdAt(review.getCreatedAt())
                    .isMine(review.getUserId().equals(userId))
                    .placename(placeName)
                    .build();
        });
    }
}

