package com.cloudingYo.barrierFree.review.repository;

import com.cloudingYo.barrierFree.review.document.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ReviewMongoRepository extends MongoRepository<Review, Long> {
    Review findByPlaceKeyAndUserId(Long placeKey, Long userId); // 순서 및 타입 확인
    List<Review> findByUserId(Long userId);
    List<Review> findByPlaceKey(Long placeKey);
    Review deleteByPlaceKeyAndUserId(Long placeKey, Long userId);
    List<Review> findTop20ByUserIdOrderByCreatedAtDesc(Long userId); // userId로 최신순 20개 리뷰 조회
    //    Page<Review> findAllPaging(Pageable pageable);
    Page<Review> findByUserIdOrderByCreatedAtDesc(Long userId, Pageable pageable);
    Page<Review> findByPlaceKeyOrderByCreatedAtDesc(Long placeKey, Pageable pageable);
}
