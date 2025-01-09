package com.cloudingYo.barrierFree.review.controller;

import com.cloudingYo.barrierFree.common.entity.ApiResponse;
import com.cloudingYo.barrierFree.review.dto.req.ReviewDTO;
import com.cloudingYo.barrierFree.review.service.ReviewService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.cloudingYo.barrierFree.common.exception.enums.SuccessType.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/spring/review")
public class ReviewController {
    private final ReviewService reviewService;

    /**
     * 리뷰 작성
     */
    @PostMapping("/register")
    public ResponseEntity<ApiResponse<?>> registerReview(@RequestBody(required = false) ReviewDTO reviewDTO, HttpSession session) {
        return ResponseEntity.ok(ApiResponse.success(REGISTER_REVIEW,reviewService.createReview(reviewDTO,session)));
    }

    /**
     * 리뷰 삭제
     */
    @DeleteMapping("/delete")
    public ResponseEntity<ApiResponse<?>> deleteReview(@RequestParam Long placeKey, HttpSession session) {
        return ResponseEntity.ok(ApiResponse.success(DELETE_REVIEW,reviewService.deleteReview(placeKey,session)));
    }

    /**
     * 유저가 작성했던 리뷰를 조회
     */
    @GetMapping("/getMyallPaging")
    public ResponseEntity<ApiResponse<?>> getMyPagingReviews(@RequestParam int page,HttpSession session) {
        return ResponseEntity.ok(ApiResponse.success(GET_MY_REVIEWS,reviewService.getReviewsByUserId(session, page)));
    }

    /*
     * 장소에 대한 리뷰를 조회
     */
    @GetMapping("/getPlaceAllPaging")
    public ResponseEntity<ApiResponse<?>> getPlacePagingReviews(@RequestParam Long placeKey,@RequestParam int page,HttpSession session){
        return ResponseEntity.ok(ApiResponse.success(GET_PLACE_REVIEWS,reviewService.getReviewsByPlaceKey(placeKey, page,session)));
    }
}
