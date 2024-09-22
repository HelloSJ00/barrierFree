package com.cloudingYo.barrierFree.review.repository;

import com.cloudingYo.barrierFree.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    Review findByPlaceIdAndUserId(Long placeId, Long userId);
}
