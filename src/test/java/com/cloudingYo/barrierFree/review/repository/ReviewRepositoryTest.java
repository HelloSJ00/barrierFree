package com.cloudingYo.barrierFree.review.repository;

import com.cloudingYo.barrierFree.place.entity.Place;
import com.cloudingYo.barrierFree.review.document.Review;
import com.cloudingYo.barrierFree.user.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.annotation.Commit;


import static org.assertj.core.api.Assertions.assertThat;



@DataMongoTest
@ComponentScan(basePackages = "com.cloudingYo.barrierFree.review.repository")
@Commit
public class ReviewRepositoryTest {

//    @Autowired
//    private ReviewRepository reviewRepository;
//
//    private Long testPlaceId;
//    private Long testUserId;
//    private Review testReview;
//
//    @BeforeEach
//    public void setUp() {
//        // Place와 User는 몽고DB에 있는 경우 참조, 그렇지 않으면 Place와 User는 간단한 객체로 사용합니다.
//        testPlaceId = 1L;
//        testUserId = 2L;
//        testReview = Review.builder()
//                .placeId(testPlaceId)
//                .userId(testUserId)
//                .content("This is a test review")
//                .build();
//    }
//
//    @Test
//    public void shouldSaveReview() {
//        // Review 저장
//        Review savedReview = reviewRepository.save(testReview);
//
//        // 저장된 리뷰가 null이 아니고, 동일한 내용을 가지고 있는지 확인
//        assertThat(savedReview).isNotNull();
//        assertThat(savedReview.getContent()).isEqualTo("This is a test review");
//    }
//
//    @Test
//    public void shouldUpdateReview() {
//        // Review 저장
//        Review savedReview = reviewRepository.save(testReview);
//
//        // Review 내용 업데이트
//        savedReview.setContent("Updated review content");
//        Review updatedReview = reviewRepository.save(savedReview);
//
//        // 업데이트된 내용 확인
//        assertThat(updatedReview.getContent()).isEqualTo("Updated review content");
//    }

}