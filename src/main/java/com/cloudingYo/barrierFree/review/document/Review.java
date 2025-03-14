package com.cloudingYo.barrierFree.review.document;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.*;

import java.time.LocalDateTime;  // 날짜 필드 추가

@Document(collection = "reviews")  // MongoDB의 컬렉션 명을 지정
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@CompoundIndexes({
        @CompoundIndex(name = "place_user_idx", def = "{'placeKey': 1, 'userId': 1}", unique = true)
})
public class Review {

    @Id
    private ObjectId id;  // MongoDB의 기본 ObjectId 사용

    // 유저와 장소는 MongoDB에 저장하지 않으므로 해당 ID만 참조
    private String username;
    private Long userId;
    private Long placeKey;
    private String content;

    /*
     * 평점 1~5
     */
    private int rating;

    // 리뷰가 작성된 날짜를 저장하는 필드
    private LocalDateTime createdAt;  // MongoDB에서 사용하는 날짜 필드

    public void editRating(int rating){
        this.rating = rating;
    }

    public void editContent(String content){
        this.content = content;
    }

    // 리뷰 생성 시점의 날짜를 자동으로 추가하는 메서드
    public void setCreatedAt() {
        this.createdAt = LocalDateTime.now();
    }
}
