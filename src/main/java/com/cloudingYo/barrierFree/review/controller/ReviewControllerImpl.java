package com.cloudingYo.barrierFree.review.controller;

import com.cloudingYo.barrierFree.common.entity.ApiResponse;
import com.cloudingYo.barrierFree.review.document.Review;
import com.cloudingYo.barrierFree.review.dto.ReviewDTO;
import com.cloudingYo.barrierFree.review.dto.ReviewResponseDTO;
import com.cloudingYo.barrierFree.review.service.ReviewService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/spring/review")
public class ReviewControllerImpl implements ReviewController {
    private final ReviewService reviewService;

    @Override
    @PostMapping("/register")
    public ResponseEntity<ReviewResponseDTO<?>> registerReview(@RequestBody(required = false) ReviewDTO reviewDTO, HttpSession session) {
        /*
        * 세션에 저장된 userId를 가져와서 reviewDTO에 저장
         */
        reviewDTO.setUserId((Long) session.getAttribute("userId"));
        reviewDTO.setUsername((String) session.getAttribute("username"));

        Review review = reviewService.createReview(reviewDTO);

        if (review == null) {
            return ResponseEntity.ok(ReviewResponseDTO.fail("리뷰 등록에 실패했습니다."));
        }
        else{
            ReviewResponseDTO<Review> response = ReviewResponseDTO.<Review>builder()
                    .status(HttpStatus.OK.value())
                    .message("success")
                    .data(review)
                    .build();
            return ResponseEntity .ok(response);
        }
    }

    @Override
    @DeleteMapping("/delete")
    public ResponseEntity<ReviewResponseDTO<?>> deleteReview(@RequestParam int placeKey, HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");

        Review review = reviewService.deleteReview(placeKey ,userId);
        if (review == null) {
            return ResponseEntity.ok(ReviewResponseDTO.fail("리뷰 삭제에 실패했습니다."));
        }
        else{
            ReviewResponseDTO<Review> response = ReviewResponseDTO.<Review>builder()
                    .status(HttpStatus.OK.value())
                    .message("success")
                    .data(review)
                    .build();
            return ResponseEntity.ok(response);
        }
    }

    @GetMapping("/getMyallPaging")
    public ResponseEntity<ReviewResponseDTO<?>> getMyPagingReviews(
            @RequestParam int page,
            HttpSession session
    ) {
        // 세션에서 userId 가져오기
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null); // 인증되지 않은 경우
        }

        // 리뷰 페이징 데이터 가져오기
        Page<ReviewDTO> reviewPage = reviewService.getReviewsByUserId(userId, page);

        // ReviewResponseDTO에 페이징된 리뷰와 추가 정보 설정
        ReviewResponseDTO<Page<ReviewDTO>> responseDTO = new ReviewResponseDTO<>();
        responseDTO.setData(reviewPage);
        responseDTO.setMessage("Paged reviews for user");
        responseDTO.setStatus(HttpStatus.OK.value());

        return ResponseEntity.ok(responseDTO);
    }

    @Override
    @GetMapping("/getPlaceAllPaging")
    public ResponseEntity<ReviewResponseDTO<?>> getPlacePagingReviews(@RequestParam Long placeKey,@RequestParam int page,HttpSession session){
        Long userId = (Long) session.getAttribute("userId");
        // 리뷰 페이징 데이터 가져오기
        Page<ReviewDTO> reviewPage = reviewService.getReviewsByPlaceKey(placeKey, page,userId);

        // ReviewResponseDTO에 페이징된 리뷰와 추가 정보 설정
        ReviewResponseDTO<Page<ReviewDTO>> responseDTO = new ReviewResponseDTO<>();
        responseDTO.setData(reviewPage);
        responseDTO.setMessage("Paged reviews for user");
        responseDTO.setStatus(HttpStatus.OK.value());

        return ResponseEntity.ok(responseDTO);
    }
}
