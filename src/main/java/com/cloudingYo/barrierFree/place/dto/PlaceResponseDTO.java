package com.cloudingYo.barrierFree.place.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PlaceResponseDTO {
    private Long id;
    private String placename;
    private Double latitude;
    private Double longitude;
    private int reviewCount;
    private Double reviewTotalScore;
}
