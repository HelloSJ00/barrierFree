package com.cloudingYo.barrierFree.review.dto;

import lombok.*;
import org.bson.types.ObjectId;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewResponseDTO {
        private Long placeId;
        private Long userId;
        private int rating;
        private String content;
}
