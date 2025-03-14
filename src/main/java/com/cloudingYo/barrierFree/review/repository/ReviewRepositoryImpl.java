package com.cloudingYo.barrierFree.review.repository;

import com.cloudingYo.barrierFree.review.document.Review;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewRepositoryImpl implements ReviewRepository{

    private final ReviewMongoRepository reviewMongoRepository;

    @Override
    public Review saveReview(Review review){
        return reviewMongoRepository.save(review);
    }

    @Override
    public Review findByPlaceKeyAndUserId(Long placeKey, Long userId) {
        return reviewMongoRepository.findByPlaceKeyAndUserId(placeKey,userId);
    }

    @Override
    public List<Review> findByUserId(Long userId) {
        return reviewMongoRepository.findByUserId(userId);
    }

    @Override
    public List<Review> findByPlaceKey(Long placeKey) {
        return reviewMongoRepository.findByPlaceKey(placeKey);
    }

    @Override
    public Review deleteByPlaceKeyAndUserId(Long placeKey, Long userId) {
        return reviewMongoRepository.deleteByPlaceKeyAndUserId(placeKey,userId);
    }

    @Override
    public List<Review> findTop20ByUserIdOrderByCreatedAtDesc(Long userId) {
        return reviewMongoRepository.findTop20ByUserIdOrderByCreatedAtDesc(userId);
    }

    @Override
    public Page<Review> findByUserIdOrderByCreatedAtDesc(Long userId, Pageable pageable) {
        return reviewMongoRepository.findByUserIdOrderByCreatedAtDesc(userId,pageable);
    }

    @Override
    public Page<Review> findByPlaceKeyOrderByCreatedAtDesc(Long placeKey, Pageable pageable) {
        return reviewMongoRepository.findByPlaceKeyOrderByCreatedAtDesc(placeKey,pageable);
    }
}
