package com.cloudingYo.barrierFree.review.service;

import com.cloudingYo.barrierFree.place.entity.Place;
import com.cloudingYo.barrierFree.place.repository.PlaceRepository;
import com.cloudingYo.barrierFree.review.document.Review;
import com.cloudingYo.barrierFree.review.dto.ReviewDTO;
import com.cloudingYo.barrierFree.review.dto.ReviewRequestDTO;
import com.cloudingYo.barrierFree.review.exception.ReviewAlreadyExistsException;
import com.cloudingYo.barrierFree.review.repository.ReviewRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.mongodb.MongoWriteException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final PlaceRepository placeRepository;
    private final WebClient webClient;

    @Override
    public Mono<Review> createReviewAsync(ReviewDTO reviewDTO) {
        if (reviewDTO.getPlaceKey() == -1 || reviewDTO.getUserId() == null) {
            return Mono.error(new IllegalArgumentException("placeId와 userId는 필수 값입니다."));
        }

        // 중복 리뷰 검사 (동기 방식)
        Review existingReview = reviewRepository.findByPlaceKeyAndUserId(reviewDTO.getPlaceKey(), reviewDTO.getUserId());
        if (existingReview != null) {
            log.warn("Review already exists for placeKey: {}, userId: {}", reviewDTO.getPlaceKey(), reviewDTO.getUserId());
            return Mono.error(new ReviewAlreadyExistsException("이 장소에 대한 리뷰는 이미 존재합니다."));
        }

        // 필요한 필드만 포함하는 ReviewRequestDTO 생성
        ReviewRequestDTO requestDTO = ReviewRequestDTO.builder()
                .placeKey(reviewDTO.getPlaceKey())
                .userId(reviewDTO.getUserId())
                .rating(reviewDTO.getRating())
                .content(reviewDTO.getContent())
                .build();

        // WebClient 비동기 요청 (논블로킹)
        Mono<JsonNode> webClientCall = webClient.post()
                .uri("/update_recommend")
                .bodyValue(requestDTO)
                .retrieve()
                .bodyToMono(JsonNode.class)
                .doOnNext(response -> log.info("Received response: {}", response))
                .doOnError(e -> log.error("Error during WebClient call for placeKey: {}, userId: {}", reviewDTO.getPlaceKey(), reviewDTO.getUserId(), e));

        // 동기 방식으로 데이터베이스에 리뷰 저장
        Mono<Review> reviewSave = Mono.fromCallable(() -> {
            Place place = placeRepository.findByPlaceKey(reviewDTO.getPlaceKey())
                    .orElseThrow(() -> new IllegalArgumentException("Place not found with the given placeKey"));

            Review review = Review.builder()
                    .userId(reviewDTO.getUserId())
                    .placeKey(reviewDTO.getPlaceKey())
                    .username(reviewDTO.getUsername())
                    .content(reviewDTO.getContent())
                    .rating(reviewDTO.getRating())
                    .createdAt(LocalDateTime.now())
                    .build();

            return reviewRepository.save(review);
        });

        // WebClient 호출과 리뷰 저장이 모두 완료되면 최종 Review를 반환
        return Mono.zip(webClientCall, reviewSave)
                .map(tuple -> tuple.getT2()) // 리뷰 저장된 Mono<Review>를 반환
                .onErrorResume(e -> {
                    log.error("Error during transaction, rolling back. Cause: {}", e.getMessage());
                    // 트랜잭션 롤백을 위해 에러를 반환
                    return Mono.error(e);
                });
    }

    @Override
    public Review deleteReview(int placeKey, Long userId) {
        return reviewRepository.deleteByPlaceKeyAndUserId(placeKey, userId);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ReviewDTO> getReviewsByUserId(Long userId, int page) {
        Pageable pageable = PageRequest.of(page, 5);
        Page<Review> reviewPage = reviewRepository.findByUserIdOrderByCreatedAtDesc(userId, pageable);

        return reviewPage.map(review -> {
            Place place = placeRepository.findByPlaceKey(review.getPlaceKey())
                    .orElse(null);
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
    public Page<ReviewDTO> getReviewsByPlaceKey(Long placeKey, int page,Long userId) {
        Pageable pageable = PageRequest.of(page, 5, Sort.by(Sort.Direction.DESC, "createdAt"));
        Page<Review> reviewPage = reviewRepository.findByPlaceKeyOrderByCreatedAtDesc(placeKey, pageable);

        return reviewPage.map(review -> {
            Place place = placeRepository.findByPlaceKey(review.getPlaceKey())
                    .orElse(null);
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

