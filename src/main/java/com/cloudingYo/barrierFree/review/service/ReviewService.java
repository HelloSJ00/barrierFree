package com.cloudingYo.barrierFree.review.service;

import com.cloudingYo.barrierFree.review.document.Review;
import com.cloudingYo.barrierFree.review.dto.ReviewDTO;
import com.cloudingYo.barrierFree.review.dto.ReviewResponseDTO;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ReviewService {
    ReviewDTO getReview(int placeKey, Long userId);
    List<ReviewDTO> getReviews(int placeKey, Long userId);
    List<ReviewDTO> getMyReviews(Long userId);
    Review createReview(ReviewDTO reviewDTO);
    Review updateReview(ReviewDTO reviewDTO);
    Review deleteReview(int placeKey, Long userId);
    Page<ReviewDTO> getReviewsByUserId(Long userId, int page);
    Page<ReviewDTO> getReviewsByPlaceKey(Long placeKey, int page,Long userId);
}
