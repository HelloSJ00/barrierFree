package com.cloudingYo.barrierFree.review.service;

import com.cloudingYo.barrierFree.review.document.Review;
import com.cloudingYo.barrierFree.review.dto.ReviewDTO;
import com.cloudingYo.barrierFree.review.dto.ReviewResponseDTO;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ReviewService {
    Review createReview(ReviewDTO reviewDTO);
    Review deleteReview(int placeKey, Long userId);
    Page<ReviewDTO> getReviewsByUserId(Long userId, int page);
    Page<ReviewDTO> getReviewsByPlaceKey(Long placeKey, int page,Long userId);
}
