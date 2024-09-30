package com.cloudingYo.barrierFree.review.controller;

import com.cloudingYo.barrierFree.review.dto.ReviewDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

public interface ReviewController {
    ResponseEntity<?> getReview(Long placeId);
    ResponseEntity<?> createReview(@RequestParam ReviewDTO reviewDTO);
    ResponseEntity<?> updateReview(@RequestParam ReviewDTO reviewDTO);
    ResponseEntity<?> deleteReview(@RequestParam ReviewDTO reviewDTO);
}
