package com.cloudingYo.barrierFree.review.dto;
import lombok.*;
import org.bson.types.ObjectId;

@Getter
@Setter
@Builder
@NoArgsConstructor  // 기본 생성자 추가
@AllArgsConstructor // 모든 필드를 받는 생성자 추가
public class ReviewDTO {
        private Long placeId;
        private Long userId;
        private int rating;
        private String content;
}