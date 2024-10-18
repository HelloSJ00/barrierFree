package com.cloudingYo.barrierFree.review.service;

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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;

    @Override
    @Transactional(readOnly = true)
    public ReviewDTO getReview(Long placeId, Long userId){
        Review review = reviewRepository.findByPlaceIdAndUserId(placeId, userId);
        return ReviewDTO.builder()
                .placeId(review.getPlaceId())
                .username(review.getUsername())
                .userId(review.getUserId())
                .rating(review.getRating())
                .content(review.getContent())
                .build();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReviewDTO> getReviews(Long placeId, Long userId) {
        return reviewRepository.findByPlaceId(placeId)
                .stream()
                .map(review -> ReviewDTO.builder()
                        .placeId(review.getPlaceId())
                        .PLACE_KEY(review.getPLACE_KEY())
                        .userId(review.getUserId())
                        .username(review.getUsername())
                        .rating(review.getRating())
                        .content(review.getContent())
                        .isMine(review.getUserId().equals(userId)) // review의 userId와 인자의 userId 비교
                        .build())
                .toList();
    }


    @Override
    public Review createReview(ReviewDTO reviewDTO) {
        if (reviewDTO.getPlaceId() == null || reviewDTO.getUserId() == null) {
            throw new IllegalArgumentException("placeId와 userId는 필수 값입니다.");
        }

        // 1. userId와 placeId가 동일한 리뷰가 이미 존재하는지 확인
        log.debug("Checking if review already exists for placeId: {}, userId: {}", reviewDTO.getPlaceId(), reviewDTO.getUserId());
        Review existingReview = reviewRepository.findByPlaceIdAndUserId(reviewDTO.getPlaceId(),reviewDTO.getUserId());
        if (existingReview != null) {
            log.warn("Review already exists for placeId: {}, userId: {}", reviewDTO.getPlaceId(), reviewDTO.getUserId());
            throw new ReviewAlreadyExistsException("이 장소에 대한 리뷰는 이미 존재합니다.");
        }

        // 2. 리뷰 생성 및 저장
        try {
            log.info("Creating new review for placeId: {}, userId: {}", reviewDTO.getPlaceId(), reviewDTO.getUserId());
            Review review = Review.builder()
                    .userId(reviewDTO.getUserId())
                    .placeId(reviewDTO.getPlaceId())
                    .username(reviewDTO.getUsername())
                    .content(reviewDTO.getContent())
                    .rating(reviewDTO.getRating())
                    .build();
            return reviewRepository.save(review);
        } catch (MongoWriteException e) {
            log.error("Error occurred while saving review for placeId: {}, userId: {}, error: {}", reviewDTO.getPlaceId(), reviewDTO.getUserId(), e.getMessage());

            throw new RuntimeException("리뷰 저장 중 오류가 발생했습니다.");
        }
    }


    @Override
    public Review updateReview(ReviewDTO reviewDTO){
        Review byPlaceIdAndUserId = reviewRepository.findByPlaceIdAndUserId(reviewDTO.getPlaceId(), reviewDTO.getUserId());
        byPlaceIdAndUserId.editRating(reviewDTO.getRating());
        byPlaceIdAndUserId.editContent(reviewDTO.getContent());
        return reviewRepository.save(byPlaceIdAndUserId);
    }

    @Override
    public Review deleteReview(Long placeId, Long userId){
        return reviewRepository.deleteByPlaceIdAndUserId(placeId, userId);
    }

}
