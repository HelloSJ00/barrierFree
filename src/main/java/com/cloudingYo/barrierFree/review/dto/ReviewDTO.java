package com.cloudingYo.barrierFree.review.dto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ReviewDTO {
        private Long id;
        private Long placeId;
        private Long userId;
        private int rating;
        private String content;
}
