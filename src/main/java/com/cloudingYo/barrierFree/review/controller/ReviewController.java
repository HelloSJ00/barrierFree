package com.cloudingYo.barrierFree.review.controller;

import com.cloudingYo.barrierFree.common.entity.ApiResponse;
import com.cloudingYo.barrierFree.review.dto.ReviewDTO;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface ReviewController {
    ResponseEntity<ApiResponse<?>> getReview(Long placeId, Long userId);
    ResponseEntity<ApiResponse<?>> getReviews(Long placeId);
    ResponseEntity<ApiResponse<?>> registerReview(@RequestBody ReviewDTO reviewDTO, HttpSession session);
    ResponseEntity<ApiResponse<?>> updateReview(@RequestBody ReviewDTO reviewDTO, HttpSession session);
    ResponseEntity<ApiResponse<?>> deleteReview(@RequestBody ReviewDTO reviewDTO, HttpSession session);
}
