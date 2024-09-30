package com.cloudingYo.barrierFree.review.service;

import com.cloudingYo.barrierFree.review.document.Review;
import com.cloudingYo.barrierFree.review.dto.ReviewDTO;
import com.cloudingYo.barrierFree.review.dto.ReviewResponseDTO;

import java.util.List;

public interface ReviewService {
    List<ReviewResponseDTO> getReviews(Long id);
    Review createReview(ReviewDTO reviewDTO);
    Review updateReview(ReviewDTO reviewDTO);
    boolean deleteReview(Long id);
}
