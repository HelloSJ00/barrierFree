package com.cloudingYo.barrierFree.infraNearbyPlace.entity;

import com.cloudingYo.barrierFree.infra.entity.Infra;
import com.cloudingYo.barrierFree.place.entity.Place;
import jakarta.persistence.*;
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
public class InfraNearbyPlace {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "infra_nearby_place_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "infra_id", nullable = false)
    private Place place;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "place_id", nullable = false)
    private Infra infra;
}
