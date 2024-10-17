package com.cloudingYo.barrierFree.favoritePlace.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FavoritePlaceDTO {
    private Long placeId;
    private Long userId;
}
