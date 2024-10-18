package com.cloudingYo.barrierFree.review.dto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.bson.types.ObjectId;

@Getter
@Setter
@Builder
@NoArgsConstructor  // 기본 생성자 추가
@AllArgsConstructor // 모든 필드를 받는 생성자 추가
public class ReviewDTO {
        private Long placeId;
        @JsonProperty("place_KEY") // JSON에서 place_KEY와 매핑
        private Long PLACE_KEY;
        private Long userId;
        private String username;
        private int rating;
        private String content;
        private boolean isMine;
}
