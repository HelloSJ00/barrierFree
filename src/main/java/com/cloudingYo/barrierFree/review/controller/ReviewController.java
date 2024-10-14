package com.cloudingYo.barrierFree.review.controller;

import com.cloudingYo.barrierFree.common.entity.ApiResponse;
import com.cloudingYo.barrierFree.review.dto.ReviewDTO;
import com.cloudingYo.barrierFree.review.dto.ReviewResponseDTO;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface ReviewController {
    ResponseEntity<ReviewResponseDTO<?>> getReview(Long placeId, Long userId);
    ResponseEntity<ReviewResponseDTO<?>> getReviews(Long placeId);
    ResponseEntity<ReviewResponseDTO<?>> registerReview(@RequestBody ReviewDTO reviewDTO, HttpSession session);
    ResponseEntity<ReviewResponseDTO<?>> updateReview(@RequestBody ReviewDTO reviewDTO, HttpSession session);
    ResponseEntity<ReviewResponseDTO<?>> deleteReview(@RequestBody ReviewDTO reviewDTO, HttpSession session);
}
