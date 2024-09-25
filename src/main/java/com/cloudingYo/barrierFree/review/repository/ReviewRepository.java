package com.cloudingYo.barrierFree.review.repository;

import com.cloudingYo.barrierFree.review.document.Review;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ReviewRepository extends MongoRepository<Review, Long> {
    Review findByPlaceIdAndUserId(Long placeId, Long userId);
    Review findByContent(String content);
}
