package com.cloudingYo.barrierFree.review.service;

import com.cloudingYo.barrierFree.review.document.Review;
import com.cloudingYo.barrierFree.review.dto.ReviewDTO;
import com.cloudingYo.barrierFree.review.dto.ReviewResponseDTO;
import com.cloudingYo.barrierFree.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
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
    public List<ReviewResponseDTO> getReviews(Long id){
        return reviewRepository.findByPlaceId(id)
                .stream().map(review -> ReviewResponseDTO.builder()
                        .id(review.getId())
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
        Review review = Review.builder()
                .placeId(reviewDTO.getPlaceId())
                .userId(reviewDTO.getUserId())
                .rating(reviewDTO.getRating())
                .content(reviewDTO.getContent())
                .build();
        return reviewRepository.save(review);
    }

    @Override
    public Review updateReview(ReviewDTO reviewDTO){
        Review review = reviewRepository.findById(reviewDTO.getId()).orElseThrow();
        review.editPlaceId(reviewDTO.getPlaceId());
        review.editRating(reviewDTO.getRating());
        review.editContent(reviewDTO.getContent());
        return reviewRepository.save(review);
    }

    @Override
    public boolean deleteReview(Long id){
        reviewRepository.deleteById(id);
        return true;
    }

}
