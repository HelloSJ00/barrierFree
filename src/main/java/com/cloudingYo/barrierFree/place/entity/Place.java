package com.cloudingYo.barrierFree.place.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.GenerationType.IDENTITY;

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

    private Long placeKey;
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
