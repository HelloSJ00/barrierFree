package com.cloudingYo.barrierFree.place.dto;

import lombok.*;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PlaceWithBookmarkDTO {

    private String PLACE_NM;
    private String en_placename;
    private int PLACE_KEY;
    private Long REC_SCORE;
    private String category;
    private Double latitude;
    private Double longitude;
//    private int reviewCount;
    private boolean bookmarked;
}
