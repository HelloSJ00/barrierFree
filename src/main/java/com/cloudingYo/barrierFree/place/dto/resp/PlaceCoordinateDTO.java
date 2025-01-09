package com.cloudingYo.barrierFree.place.dto.resp;

import lombok.*;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PlaceCoordinateDTO {
    private Long PLACE_KEY;
    private Double latitude;
    private Double longitude;
}
