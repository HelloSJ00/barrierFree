package com.cloudingYo.barrierFree.review.service;

import com.cloudingYo.barrierFree.place.entity.Place;
import com.cloudingYo.barrierFree.place.repository.PlaceRepository;
import com.cloudingYo.barrierFree.review.document.Review;
import com.cloudingYo.barrierFree.review.dto.ReviewDTO;
import com.cloudingYo.barrierFree.review.dto.ReviewResponseDTO;
import com.cloudingYo.barrierFree.review.exception.ReviewAlreadyExistsException;
import com.cloudingYo.barrierFree.review.repository.ReviewRepository;
import com.mongodb.DuplicateKeyException;
import com.mongodb.MongoWriteException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final PlaceRepository placeRepository;

    @Override
    @Transactional(readOnly = true)
    public ReviewDTO getReview(int placeKey, Long userId){
        Review review = reviewRepository.findByPlaceKeyAndUserId(placeKey, userId);
        Place place = placeRepository.findByPlaceKey(placeKey);
        return ReviewDTO.builder()
                .placeKey(review.getPlaceKey())
                .username(review.getUsername())
                .placename(place.getPlacename())
                .userId(review.getUserId())
                .rating(review.getRating())
                .content(review.getContent())
                .build();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReviewDTO> getReviews(int placeKey, Long userId) {
        Place place = placeRepository.findByPlaceKey(placeKey);
        return reviewRepository.findByPlaceKey(placeKey)
                .stream()
                .map(review -> ReviewDTO.builder()
                        .placeKey(review.getPlaceKey())
                        .userId(review.getUserId())
                        .username(review.getUsername())
                        .placename(place.getPlacename())
                        .rating(review.getRating())
                        .content(review.getContent())
                        .createdAt(review.getCreatedAt())
                        .isMine(review.getUserId().equals(userId)) // review의 userId와 인자의 userId 비교
                        .build())
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReviewDTO> getMyReviews(Long userId) {
        return reviewRepository.findTop20ByUserIdOrderByCreatedAtDesc(userId)
                .stream()
                .map(review -> {
                    // placeKey로 장소를 조회하여 placeName 가져오기
                    Place place = placeRepository.findByPlaceKey(review.getPlaceKey());
                    String placeName = place != null ? place.getPlacename() : "Unknown Place"; // 장소가 없으면 기본값 설정

                    return ReviewDTO.builder()
                            .placeKey(review.getPlaceKey())
                            .userId(review.getUserId())
                            .username(review.getUsername())
                            .rating(review.getRating())
                            .content(review.getContent())
                            .createdAt(review.getCreatedAt())
                            .isMine(review.getUserId().equals(userId))  // review의 userId와 인자의 userId 비교
                            .placename(placeName)  // placeName 추가
                            .build();
                })
                .toList();
    }


    @Override
    public Review createReview(ReviewDTO reviewDTO) {
        if (reviewDTO.getPlaceKey() == -1 || reviewDTO.getUserId() == null) {
            throw new IllegalArgumentException("placeId와 userId는 필수 값입니다.");
        }

        // 1. userId와 placeId가 동일한 리뷰가 이미 존재하는지 확인
        log.debug("Checking if review already exists for placeKey: {}, userId: {}", reviewDTO.getPlaceKey(), reviewDTO.getUserId());
        Review existingReview = reviewRepository.findByPlaceKeyAndUserId(reviewDTO.getPlaceKey(), reviewDTO.getUserId());
        if (existingReview != null) {
            log.warn("Review already exists for placeKey: {}, userId: {}", reviewDTO.getPlaceKey(), reviewDTO.getUserId());
            throw new ReviewAlreadyExistsException("이 장소에 대한 리뷰는 이미 존재합니다.");
        }

        // 2. 리뷰 생성 및 저장
        try {
            log.info("Creating new review for placeKey: {}, userId: {}", reviewDTO.getPlaceKey(), reviewDTO.getUserId());

            Review review = Review.builder()
                    .userId(reviewDTO.getUserId())
                    .placeKey(reviewDTO.getPlaceKey())
                    .username(reviewDTO.getUsername())
                    .content(reviewDTO.getContent())
                    .rating(reviewDTO.getRating())
                    .createdAt(LocalDateTime.now())  // 리뷰 생성 시점을 기록
                    .build();

            Place place = placeRepository.findByPlaceKey(reviewDTO.getPlaceKey());

            return reviewRepository.save(review);
        } catch (MongoWriteException e) {
            log.error("Error occurred while saving review for placeKey: {}, userId: {}, error: {}", reviewDTO.getPlaceKey(), reviewDTO.getUserId(), e.getMessage());

            throw new RuntimeException("리뷰 저장 중 오류가 발생했습니다.");
        }
    }



    @Override
    public Review updateReview(ReviewDTO reviewDTO){
        Review byPlaceKeyAndUserId = reviewRepository.findByPlaceKeyAndUserId(reviewDTO.getPlaceKey(), reviewDTO.getUserId());
        byPlaceKeyAndUserId.editRating(reviewDTO.getRating());
        byPlaceKeyAndUserId.editContent(reviewDTO.getContent());
        return reviewRepository.save(byPlaceKeyAndUserId);
    }

    @Override
    public Review deleteReview(int placeKey, Long userId){
        return reviewRepository.deleteByPlaceKeyAndUserId(placeKey, userId);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ReviewDTO> getReviewsByUserId(Long userId, int page) {
        int pageSize = 5; // 페이지당 리뷰 개수 설정
        Pageable pageable = PageRequest.of(page, pageSize);

        // MongoDB에서 userId와 일치하는 리뷰를 최신순으로 페이징하여 가져옴
        Page<Review> reviewPage = reviewRepository.findByUserIdOrderByCreatedAtDesc(userId, pageable);

        // 리뷰를 ReviewDTO로 변환하여 반환
        return reviewPage.map(review -> {
            // placeKey로 장소 조회 후 placeName 가져오기
            Place place = placeRepository.findByPlaceKey(review.getPlaceKey());
            String placeName = place != null ? place.getPlacename() : "Unknown Place";

            return ReviewDTO.builder()
                    .placeKey(review.getPlaceKey())
                    .userId(review.getUserId())
                    .username(review.getUsername())
                    .rating(review.getRating())
                    .content(review.getContent())
                    .createdAt(review.getCreatedAt())
                    .isMine(true) // 필요에 따라 수정
                    .placename(placeName) // placeName 추가
                    .build();
        });
    }

    public Page<ReviewDTO> getReviewsByPlaceKey(Long placeKey, int page) {
        Pageable pageable = PageRequest.of(page, 5, Sort.by(Sort.Direction.DESC, "createdAt"));
        Page<Review> reviewPage = reviewRepository.findByPlaceKeyOrderByCreatedAtDesc(placeKey, pageable);
        // 리뷰를 ReviewDTO로 변환하여 반환
        return reviewPage.map(review -> {
            // placeKey로 장소 조회 후 placeName 가져오기
            Place place = placeRepository.findByPlaceKey(review.getPlaceKey());
            String placeName = place != null ? place.getPlacename() : "Unknown Place";

            return ReviewDTO.builder()
                    .placeKey(review.getPlaceKey())
                    .userId(review.getUserId())
                    .username(review.getUsername())
                    .rating(review.getRating())
                    .content(review.getContent())
                    .createdAt(review.getCreatedAt())
                    .isMine(true) // 필요에 따라 수정
                    .placename(placeName) // placeName 추가
                    .build();
        });
    }
}
