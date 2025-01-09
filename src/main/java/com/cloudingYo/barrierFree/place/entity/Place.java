package com.cloudingYo.barrierFree.place.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import static jakarta.persistence.GenerationType.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class Place {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "place_id")
    private Long id;

    private int placeKey;
    private String placename;
    private String en_placename;
    private String category;
    private Double latitude;
    private Double longitude;

    /**
     * 0: int값이라 별도의 설정이 없다면 기본값이 0
     */
    private int reviewCount;

    private int totalScroe;

    public Place(String placename) {
        this.placename = placename;
    }
}
