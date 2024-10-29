package com.cloudingYo.barrierFree.favoritePlace.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Setter
public class FavoritePlaceResponseDTO<T> {
    private String message;
    private int status;
    private T data;
}
