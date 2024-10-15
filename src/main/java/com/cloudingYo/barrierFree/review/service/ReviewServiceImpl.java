package com.cloudingYo.barrierFree.review.service;

import com.cloudingYo.barrierFree.review.document.Review;
import com.cloudingYo.barrierFree.review.dto.ReviewDTO;
import com.cloudingYo.barrierFree.review.dto.ReviewResponseDTO;
import com.cloudingYo.barrierFree.review.repository.ReviewRepository;
import com.mongodb.DuplicateKeyException;
import com.mongodb.MongoWriteException;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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
                .userId(review.getUserId())
                .rating(review.getRating())
                .content(review.getContent())
                .build();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReviewDTO> getReviews(Long placeId){
        return reviewRepository.findByPlaceId(placeId)
                .stream().map(review -> ReviewDTO.builder()
                        .placeId(review.getPlaceId())
                        .userId(review.getUserId())
                        .rating(review.getRating())
                        .content(review.getContent())
                        .build())
                .toList();
    }

    @Override
    public Review createReview(ReviewDTO reviewDTO) {
        if (reviewDTO.getPlaceId() == null || reviewDTO.getUserId() == null) {
            throw new IllegalArgumentException("placeId와 userId는 필수 값입니다.");
        }

        // 1. userId와 placeId가 동일한 리뷰가 이미 존재하는지 확인
        Review existingReview = reviewRepository.findByPlaceIdAndUserId(reviewDTO.getUserId(), reviewDTO.getPlaceId());
        if (existingReview != null) {
            throw new RuntimeException("이 장소에 대한 리뷰는 이미 존재합니다.");
        }

        // 2. 리뷰 생성 및 저장
        try {
            Review review = Review.builder()
                    .userId(reviewDTO.getUserId())
                    .placeId(reviewDTO.getPlaceId())
                    .content(reviewDTO.getContent())
                    .rating(reviewDTO.getRating())
                    .build();
            return reviewRepository.save(review);
        } catch (MongoWriteException e) {
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
