package com.cloudingYo.barrierFree.review.controller;

import com.cloudingYo.barrierFree.review.dto.ReviewDTO;
import com.cloudingYo.barrierFree.review.service.ReviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/review")
public class ReviewControllerImpl implements ReviewController {
    private final ReviewService reviewService;

    @Override
    @GetMapping("/getone")
    public ResponseEntity<?> getReview(Long placeId,Long userId) {
        return ResponseEntity.ok(reviewService.getReviews(placeId));
    }

    @Override
    @GetMapping("/getall")
    public ResponseEntity<?> getReviews(Long placeId) {
        return ResponseEntity.ok(reviewService.getReviews(placeId));
    }


    @Override
    @PostMapping("/register")
    public ResponseEntity<?> registerReview(@RequestBody(required = false) ReviewDTO reviewDTO) {
        return ResponseEntity.ok(reviewService.createReview(reviewDTO));
    }

    @Override
    @PostMapping("/update")
    public ResponseEntity<?> updateReview(@RequestBody ReviewDTO reviewDTO) {
        return ResponseEntity.ok(reviewService.updateReview(reviewDTO));
    }

    @Override
    @PostMapping("/delete")
    public ResponseEntity<?> deleteReview(@RequestBody ReviewDTO reviewDTO) {
        return ResponseEntity.ok(reviewService.deleteReview(reviewDTO.getPlaceId(), reviewDTO.getUserId()));
    }
}
