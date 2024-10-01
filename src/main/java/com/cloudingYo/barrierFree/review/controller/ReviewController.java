package com.cloudingYo.barrierFree.review.controller;

import com.cloudingYo.barrierFree.review.dto.ReviewDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface ReviewController {
    ResponseEntity<?> getReview(Long placeId, Long userId);
    ResponseEntity<?> getReviews(Long placeId);
    ResponseEntity<?> registerReview(@RequestBody ReviewDTO reviewDTO);
    ResponseEntity<?> updateReview(@RequestBody ReviewDTO reviewDTO);
    ResponseEntity<?> deleteReview(@RequestBody ReviewDTO reviewDTO);
}
