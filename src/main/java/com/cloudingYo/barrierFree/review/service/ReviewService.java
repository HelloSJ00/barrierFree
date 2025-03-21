package com.cloudingYo.barrierFree.review.service;

import com.cloudingYo.barrierFree.review.document.Review;
import com.cloudingYo.barrierFree.review.dto.req.ReviewDTO;
import jakarta.servlet.http.HttpSession;
import org.springframework.data.domain.Page;

public interface ReviewService {
    Review createReview(ReviewDTO reviewDTO,HttpSession session) ;
    Review deleteReview(Long placeKey, HttpSession session);
    Page<ReviewDTO> getReviewsByUserId(HttpSession session, int page);
    Page<ReviewDTO> getReviewsByPlaceKey(Long placeKey, int page,HttpSession session);
}
