package com.cloudingYo.barrierFree.review.dto.req;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor  // 기본 생성자 추가
@AllArgsConstructor // 모든 필드를 받는 생성자 추가
public class ReviewDTO {
        private Long placeKey;
        private String placename;
        private Long userId;
        private String username;
        private int rating;
        private String content;
        private boolean isMine;
        private LocalDateTime createdAt;
}
