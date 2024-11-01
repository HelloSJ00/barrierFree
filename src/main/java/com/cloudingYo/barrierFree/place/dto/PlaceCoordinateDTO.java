package com.cloudingYo.barrierFree.place.dto;

import lombok.*;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PlaceCoordinateDTO {
    private int PLACE_KEY;
    private Double latitude;
    private Double longitude;
}
