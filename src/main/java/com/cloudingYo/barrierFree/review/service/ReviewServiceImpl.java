package com.cloudingYo.barrierFree.review.service;

import com.cloudingYo.barrierFree.review.document.Review;
import com.cloudingYo.barrierFree.review.dto.ReviewDTO;
import com.cloudingYo.barrierFree.review.dto.ReviewResponseDTO;
import com.cloudingYo.barrierFree.review.repository.ReviewRepository;
import com.mongodb.DuplicateKeyException;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    public Review createReview(ReviewDTO reviewDTO){
        if (reviewDTO.getPlaceId() == null || reviewDTO.getUserId() == null) {
            throw new IllegalArgumentException("placeId와 userId는 필수 값입니다.");
        }
        try {
            Review review = Review.builder()
                    .userId(reviewDTO.getUserId())
                    .placeId(reviewDTO.getPlaceId())
                    .content(reviewDTO.getContent())
                    .rating(reviewDTO.getRating())
                    .build();
            return reviewRepository.save(review);
        } catch (DuplicateKeyException e) {
            throw new RuntimeException("이 사용자와 장소에 대한 리뷰는 이미 존재합니다.");
        }
    }

    @Override
    public Review updateReview(ReviewDTO reviewDTO){
        Review newReview = Review.builder()
                .userId(reviewDTO.getUserId())
                .placeId(reviewDTO.getPlaceId())
                .content(reviewDTO.getContent())
                .rating(reviewDTO.getRating())
                .build();
        return reviewRepository.save(newReview);
    }

    @Override
    public Review deleteReview(Long placeId, Long userId){
        return reviewRepository.deleteByPlaceIdAndUserId(placeId, userId);
    }

}
