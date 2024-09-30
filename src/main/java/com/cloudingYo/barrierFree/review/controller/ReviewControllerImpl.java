package com.cloudingYo.barrierFree.review.controller;

import com.cloudingYo.barrierFree.review.dto.ReviewDTO;
import com.cloudingYo.barrierFree.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/review")
public class ReviewControllerImpl implements ReviewController {
    private final ReviewService reviewService;

    @Override
    public ResponseEntity<?> getReview(Long placeId) {
        return ResponseEntity.ok(reviewService.getReviews(placeId));
    }

    @Override
    public ResponseEntity<?> createReview(@RequestParam ReviewDTO reviewDTO) {
        return ResponseEntity.ok(reviewService.createReview(reviewDTO));
    }

    @Override
    public ResponseEntity<?> updateReview(@RequestParam ReviewDTO reviewDTO) {
        return ResponseEntity.ok(reviewService.updateReview(reviewDTO));
    }

    @Override
    public ResponseEntity<?> deleteReview(@RequestParam ReviewDTO reviewDTO) {
        return ResponseEntity.ok(reviewService.deleteReview(reviewDTO.getId()));
    }
}
