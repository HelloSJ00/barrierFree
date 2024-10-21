package com.cloudingYo.barrierFree.place.dto;

import lombok.*;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PlaceWithBookmarkDTO {

    private String placename;
    private Double latitude;
    private Double longitude;
    private int reviewCount;
    private boolean bookmarked;
}
