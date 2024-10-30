package com.cloudingYo.barrierFree.favoritePlace.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FavoritePlaceDTO {
    private int placeKey;
    private String placename;
    private boolean bookmarked;
    private Double latitude;
    private Double longitude;
}
