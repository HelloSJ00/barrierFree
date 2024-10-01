package com.cloudingYo.barrierFree.review.dto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;

@Getter
@Setter
@Builder
public class ReviewDTO {
        private ObjectId id;
        private Long placeId;
        private Long userId;
        private int rating;
        private String content;
}
