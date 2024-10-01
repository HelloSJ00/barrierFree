package com.cloudingYo.barrierFree.review.controller;

import com.cloudingYo.barrierFree.common.entity.ApiResponse;
import com.cloudingYo.barrierFree.review.document.Review;
import com.cloudingYo.barrierFree.review.dto.ReviewDTO;
import com.cloudingYo.barrierFree.review.service.ReviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/review")
public class ReviewControllerImpl implements ReviewController {
    private final ReviewService reviewService;

    @Override
    @GetMapping("/getone")
    public ResponseEntity<ApiResponse<?>> getReview(@RequestParam Long placeId, Long userId) {
        ReviewDTO review = reviewService.getReview(placeId, userId);
        if (review == null) {
            return ResponseEntity.ok(ApiResponse.fail("리뷰를 찾을 수 없습니다."));
        }
        else{
            ApiResponse<ReviewDTO> response = ApiResponse.<ReviewDTO>builder()
                    .status(HttpStatus.OK.value())
                    .message("success")
                    .data(review)
                    .build();
            return ResponseEntity.ok(response);
        }
    }

    @Override
    @GetMapping("/getall")
    public ResponseEntity<ApiResponse<?>> getReviews(@RequestParam Long placeId) {
        List<ReviewDTO> reviews = reviewService.getReviews(placeId);
        if (reviews.isEmpty()) {
            return ResponseEntity.ok(ApiResponse.fail("리뷰를 찾을 수 없습니다."));
        }
        else{
            ApiResponse<List<ReviewDTO>> response = ApiResponse.<List<ReviewDTO>>builder()
                    .status(HttpStatus.OK.value())
                    .message("success")
                    .data(reviews)
                    .build();
            return ResponseEntity.ok(response);
        }
    }


    @Override
    @PostMapping("/register")
    public ResponseEntity<ApiResponse<?>> registerReview(@RequestBody(required = false) ReviewDTO reviewDTO) {
        Review review = reviewService.createReview(reviewDTO);
        if (review == null) {
            return ResponseEntity.ok(ApiResponse.fail("리뷰 등록에 실패했습니다."));
        }
        else{
            ApiResponse<Review> response = ApiResponse.<Review>builder()
                    .status(HttpStatus.OK.value())
                    .message("success")
                    .data(review)
                    .build();
            return ResponseEntity.ok(response);
        }
    }

    @Override
    @PostMapping("/update")
    public ResponseEntity<ApiResponse<?>> updateReview(@RequestBody ReviewDTO reviewDTO) {
        Review review = reviewService.updateReview(reviewDTO);
        if (review == null) {
            return ResponseEntity.ok(ApiResponse.fail("리뷰 수정에 실패했습니다."));
        }
        else{
            ApiResponse<Review> response = ApiResponse.<Review>builder()
                    .status(HttpStatus.OK.value())
                    .message("success")
                    .data(review)
                    .build();
            return ResponseEntity.ok(response);
        }
    }

    @Override
    @DeleteMapping("/delete")
    public ResponseEntity<ApiResponse<?>> deleteReview(@RequestBody ReviewDTO reviewDTO) {
        Review review = reviewService.deleteReview(reviewDTO.getPlaceId(), reviewDTO.getUserId());
        if (review == null) {
            return ResponseEntity.ok(ApiResponse.fail("리뷰 삭제에 실패했습니다."));
        }
        else{
            ApiResponse<Review> response = ApiResponse.<Review>builder()
                    .status(HttpStatus.OK.value())
                    .message("success")
                    .data(review)
                    .build();
            return ResponseEntity.ok(response);
        }
    }
}
