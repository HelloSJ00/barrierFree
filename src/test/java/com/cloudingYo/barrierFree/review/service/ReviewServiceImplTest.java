package com.cloudingYo.barrierFree.review.service;

import com.cloudingYo.barrierFree.review.document.Review;
import com.cloudingYo.barrierFree.review.dto.ReviewDTO;
import com.cloudingYo.barrierFree.review.dto.ReviewResponseDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Commit
class ReviewServiceImplTest {

    @Autowired
    private ReviewService reviewService;

    @Test
    void createReview() {
        // given
        ReviewDTO reviewDTO = ReviewDTO.builder()

                .placeId(1L)
                .userId(1L)
                .rating(5)
                .content("좋아요")
                .build();
        // when
        Review result = reviewService.createReview(reviewDTO);
        // then
        assertEquals(reviewDTO.getPlaceId(), result.getPlaceId());
    }

    @Test
    void updateReview() {
        // given
        ReviewDTO reviewDTO = ReviewDTO.builder()
                .placeId(1L)
                .userId(3L)
                .rating(5)
                .content("좋아요")
                .build();
        Review review = reviewService.createReview(reviewDTO);
        ReviewResponseDTO findReview = reviewService.getReviews(review.getPlaceId()).get(0);

        ReviewDTO updateReviewDTO = ReviewDTO.builder()
                .id(findReview.getId())
                .placeId(findReview.getPlaceId())
                .userId(findReview.getUserId())
                .rating(4)
                .content("별로에요")
                .build();
        // when
        Review result = reviewService.updateReview(updateReviewDTO);
        // then
        assertEquals(updateReviewDTO.getRating(), result.getRating());
    }

}