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
    ResponseEntity<ReviewResponseDTO<?>> getReview(int placeKey, Long userId);
    ResponseEntity<ReviewResponseDTO<?>> getReviews(int placeKey,HttpSession session);
    ResponseEntity<ReviewResponseDTO<?>> getMyReviews(HttpSession session);
    ResponseEntity<ReviewResponseDTO<?>> registerReview(@RequestBody ReviewDTO reviewDTO, HttpSession session);
    ResponseEntity<ReviewResponseDTO<?>> updateReview(@RequestBody ReviewDTO reviewDTO, HttpSession session);
    ResponseEntity<ReviewResponseDTO<?>> deleteReview(@RequestParam int placeKey, HttpSession session);
    ResponseEntity<ReviewResponseDTO<?>> getPagingReviews(@RequestParam int page,HttpSession session);

}
