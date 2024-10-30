package com.cloudingYo.barrierFree.review.service;

import com.cloudingYo.barrierFree.place.entity.Place;
import com.cloudingYo.barrierFree.place.repository.PlaceRepository;
import com.cloudingYo.barrierFree.review.document.Review;
import com.cloudingYo.barrierFree.review.dto.ReviewDTO;
import com.cloudingYo.barrierFree.review.dto.ReviewResponseDTO;
import com.cloudingYo.barrierFree.review.exception.ReviewAlreadyExistsException;
import com.cloudingYo.barrierFree.review.repository.ReviewRepository;
import com.mongodb.DuplicateKeyException;
import com.mongodb.MongoWriteException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final PlaceRepository placeRepository;

    @Override
    @Transactional(readOnly = true)
    public ReviewDTO getReview(int placeKey, Long userId) {
        Review review = reviewRepository.findByPlaceKeyAndUserId(placeKey, userId);
        Place place = placeRepository.findByPlaceKey(placeKey)
                .orElseThrow(() -> new IllegalArgumentException("Place not found"));

        return ReviewDTO.builder()
                .placeKey(review.getPlaceKey())
                .username(review.getUsername())
                .placename(place.getPlacename())
                .userId(review.getUserId())
                .rating(review.getRating())
                .content(review.getContent())
                .build();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReviewDTO> getReviews(int placeKey, Long userId) {
        Place place = placeRepository.findByPlaceKey(placeKey)
                .orElseThrow(() -> new IllegalArgumentException("Place not found"));

        return reviewRepository.findByPlaceKey(placeKey)
                .stream()
                .map(review -> ReviewDTO.builder()
                        .placeKey(review.getPlaceKey())
                        .userId(review.getUserId())
                        .username(review.getUsername())
                        .placename(place.getPlacename())
                        .rating(review.getRating())
                        .content(review.getContent())
                        .createdAt(review.getCreatedAt())
                        .isMine(review.getUserId().equals(userId))
                        .build())
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReviewDTO> getMyReviews(Long userId) {
        return reviewRepository.findTop20ByUserIdOrderByCreatedAtDesc(userId)
                .stream()
                .map(review -> {
                    Place place = placeRepository.findByPlaceKey(review.getPlaceKey())
                            .orElse(null); // 장소가 없을 경우 null로 설정
                    String placeName = place != null ? place.getPlacename() : "Unknown Place";

                    return ReviewDTO.builder()
                            .placeKey(review.getPlaceKey())
                            .userId(review.getUserId())
                            .username(review.getUsername())
                            .rating(review.getRating())
                            .content(review.getContent())
                            .createdAt(review.getCreatedAt())
                            .isMine(review.getUserId().equals(userId))
                            .placename(placeName)
                            .build();
                })
                .toList();
    }

    @Override
    public Review createReview(ReviewDTO reviewDTO) {
        if (reviewDTO.getPlaceKey() == -1 || reviewDTO.getUserId() == null) {
            throw new IllegalArgumentException("placeId와 userId는 필수 값입니다.");
        }

        log.debug("Checking if review already exists for placeKey: {}, userId: {}", reviewDTO.getPlaceKey(), reviewDTO.getUserId());
        Review existingReview = reviewRepository.findByPlaceKeyAndUserId(reviewDTO.getPlaceKey(), reviewDTO.getUserId());
        if (existingReview != null) {
            log.warn("Review already exists for placeKey: {}, userId: {}", reviewDTO.getPlaceKey(), reviewDTO.getUserId());
            throw new ReviewAlreadyExistsException("이 장소에 대한 리뷰는 이미 존재합니다.");
        }

        try {
            log.info("Creating new review for placeKey: {}, userId: {}", reviewDTO.getPlaceKey(), reviewDTO.getUserId());

            Review review = Review.builder()
                    .userId(reviewDTO.getUserId())
                    .placeKey(reviewDTO.getPlaceKey())
                    .username(reviewDTO.getUsername())
                    .content(reviewDTO.getContent())
                    .rating(reviewDTO.getRating())
                    .createdAt(LocalDateTime.now())
                    .build();

            placeRepository.findByPlaceKey(reviewDTO.getPlaceKey())
                    .orElseThrow(() -> new IllegalArgumentException("Place not found with the given placeKey"));

            return reviewRepository.save(review);
        } catch (MongoWriteException e) {
            log.error("Error occurred while saving review for placeKey: {}, userId: {}, error: {}", reviewDTO.getPlaceKey(), reviewDTO.getUserId(), e.getMessage());
            throw new RuntimeException("리뷰 저장 중 오류가 발생했습니다.");
        }
    }

    @Override
    public Review updateReview(ReviewDTO reviewDTO) {
        Review review = reviewRepository.findByPlaceKeyAndUserId(reviewDTO.getPlaceKey(), reviewDTO.getUserId());
        review.editRating(reviewDTO.getRating());
        review.editContent(reviewDTO.getContent());
        return reviewRepository.save(review);
    }

    @Override
    public Review deleteReview(int placeKey, Long userId) {
        return reviewRepository.deleteByPlaceKeyAndUserId(placeKey, userId);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ReviewDTO> getReviewsByUserId(Long userId, int page) {
        Pageable pageable = PageRequest.of(page, 5);
        Page<Review> reviewPage = reviewRepository.findByUserIdOrderByCreatedAtDesc(userId, pageable);

        return reviewPage.map(review -> {
            Place place = placeRepository.findByPlaceKey(review.getPlaceKey())
                    .orElse(null);
            String placeName = place != null ? place.getPlacename() : "Unknown Place";

            return ReviewDTO.builder()
                    .placeKey(review.getPlaceKey())
                    .userId(review.getUserId())
                    .username(review.getUsername())
                    .rating(review.getRating())
                    .content(review.getContent())
                    .createdAt(review.getCreatedAt())
                    .isMine(true)
                    .placename(placeName)
                    .build();
        });
    }

    @Override
    public Page<ReviewDTO> getReviewsByPlaceKey(Long placeKey, int page,Long userId) {
        Pageable pageable = PageRequest.of(page, 5, Sort.by(Sort.Direction.DESC, "createdAt"));
        Page<Review> reviewPage = reviewRepository.findByPlaceKeyOrderByCreatedAtDesc(placeKey, pageable);

        return reviewPage.map(review -> {
            Place place = placeRepository.findByPlaceKey(review.getPlaceKey())
                    .orElse(null);
            String placeName = place != null ? place.getPlacename() : "Unknown Place";

            return ReviewDTO.builder()
                    .placeKey(review.getPlaceKey())
                    .userId(review.getUserId())
                    .username(review.getUsername())
                    .rating(review.getRating())
                    .content(review.getContent())
                    .createdAt(review.getCreatedAt())
                    .isMine(review.getUserId().equals(userId))
                    .placename(placeName)
                    .build();
        });
    }
}

