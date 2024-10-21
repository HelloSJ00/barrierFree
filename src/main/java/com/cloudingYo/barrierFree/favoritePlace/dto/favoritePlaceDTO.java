package com.cloudingYo.barrierFree.favoritePlace.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class favoritePlaceDTO {
    private String placename;
    private Double latitude;
    private Double longitude;
    private int reviewCount;
    private boolean bookmarked;
}
