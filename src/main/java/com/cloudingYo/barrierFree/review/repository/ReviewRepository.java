package com.cloudingYo.barrierFree.review.repository;

import com.cloudingYo.barrierFree.review.document.Review;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ReviewRepository extends MongoRepository<Review, Long> {
    Review findByPlaceKeyAndUserId(int placeKey, Long userId); // 순서 및 타입 확인
    List<Review> findByUserId(Long userId);
    List<Review> findByPlaceKey(int placeKey);
    Review deleteByPlaceKeyAndUserId(int placeKey, Long userId);
    List<Review> findTop20ByUserIdOrderByCreatedAtDesc(Long userId); // userId로 최신순 20개 리뷰 조회

}
