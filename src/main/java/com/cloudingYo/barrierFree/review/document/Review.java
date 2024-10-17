package com.cloudingYo.barrierFree.review.document;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.*;

@Document(collection = "reviews")  // MongoDB의 컬렉션 명을 지정
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@CompoundIndexes({
        @CompoundIndex(name = "user_place_unique_idx", def = "{'userId': 1, 'placeId': 1}", unique = true)
})
public class Review {

    @Id
    private ObjectId id;  // MongoDB의 기본 ObjectId 사용

    // 유저와 장소는 MongoDB에 저장하지 않으므로 해당 ID만 참조
    private String username;
    private Long userId;
    private Long placeId;
    private String content;
    /*
        * 평점 1~5
     */
    private int rating;

    public void editRating(int rating){
        this.rating = rating;
    }

    public void editContent(String content){
        this.content = content;
    }
}
