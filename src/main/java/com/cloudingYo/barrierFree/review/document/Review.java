package com.cloudingYo.barrierFree.review.document;

import com.cloudingYo.barrierFree.place.entity.Place;
import com.cloudingYo.barrierFree.user.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;
import lombok.*;

@Document(collection = "reviews")  // MongoDB의 컬렉션 명을 지정
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Review {

    @Id
    private ObjectId id;  // MongoDB의 기본 ObjectId 사용

    // 유저와 장소는 MongoDB에 저장하지 않으므로 해당 ID만 참조
    private Long userId;
    private Long placeId;
    private String content;
    /*
        * 평점 1~5
     */
    private int rating;

    public void editPlaceId(Long placeId) {
        this.placeId = placeId;
    }

    public void editContent(String content) {
        this.content = content;
    }

    public void editRating(int rating) {
        this.rating = rating;
    }
}
