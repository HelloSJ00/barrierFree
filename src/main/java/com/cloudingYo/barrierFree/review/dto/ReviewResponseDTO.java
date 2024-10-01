package com.cloudingYo.barrierFree.review.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;

@Setter
@Getter
@Builder
public class ReviewResponseDTO {
        private Long placeId;
        private Long userId;
        private int rating;
        private String content;
}
