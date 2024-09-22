package com.cloudingYo.barrierFree.review.repository;

import com.cloudingYo.barrierFree.place.entity.Place;
import com.cloudingYo.barrierFree.place.repository.PlaceRepository;
import com.cloudingYo.barrierFree.review.entity.Review;
import com.cloudingYo.barrierFree.user.entity.User;
import com.cloudingYo.barrierFree.user.repository.UserRepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ReviewRepositoryTest {

    @Autowired
    EntityManager em ;

    @Autowired ReviewRepository reviewRepository ;
    @Autowired PlaceRepository placeRepository ;
    @Autowired UserRepository userRepository ;

    @Test
    void findReviewByAll() {
        // given
        Place placeA = Place.builder()
                .placename("a")
                .latitude(1.0)
                .longitude(1.0)
                .build();
        placeRepository.save(placeA);

        User userA = User.builder()
                .username("a")
                .email("a")
                .password("a")
                .build();
        userRepository.save(userA);

        Review reviewA = Review.builder()
                .content("a")
                .place(placeRepository.findByPlacename("a"))
                .user(userRepository.findByEmail("a"))
                .build();
        Review reviewB = Review.builder()
                .content("b")
                .place(placeRepository.findByPlacename("b"))
                .user(userRepository.findByEmail("b"))
                .build();

        reviewRepository.save(reviewA);
        reviewRepository.save(reviewB);

        em.flush();
        em.clear();

        // when
        List<Review> reviews = reviewRepository.findAll();  // 모든 리뷰 찾기
        assertEquals(2, reviews.size());
    }

}