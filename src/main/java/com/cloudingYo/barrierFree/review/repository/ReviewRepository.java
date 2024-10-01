package com.cloudingYo.barrierFree.review.repository;

import com.cloudingYo.barrierFree.review.document.Review;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ReviewRepository extends MongoRepository<Review, Long> {
    Review findByPlaceIdAndUserId(Long placeId, Long userId);
    Review findByContent(String content);
    List<Review> findByPlaceId(Long placeId);
    Review deleteByPlaceIdAndUserId(Long placeId, Long userId);
}
