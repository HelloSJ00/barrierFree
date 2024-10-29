package com.cloudingYo.barrierFree.favoritePlace.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FavoritePlaceDTO {
    private String placename;
    private Double latitude;
    private Double longitude;
    private boolean bookmarked;
}
