package com.cloudingYo.barrierFree.review.service;

import com.cloudingYo.barrierFree.review.document.Review;
import com.cloudingYo.barrierFree.review.dto.ReviewDTO;
import com.cloudingYo.barrierFree.review.dto.ReviewResponseDTO;
import org.bson.types.ObjectId;

import java.util.List;

public interface ReviewService {
    ReviewDTO getReview(Long placeId, Long userId);
    List<ReviewDTO> getReviews(Long placeId);
    Review createReview(ReviewDTO reviewDTO);
    Review updateReview(ReviewDTO reviewDTO);
    Review deleteReview(Long placeId, Long userId);
}
