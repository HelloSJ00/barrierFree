package com.cloudingYo.barrierFree.favoritePlace.entity;

import com.cloudingYo.barrierFree.common.entity.BaseEntity;
import com.cloudingYo.barrierFree.place.entity.Place;
import com.cloudingYo.barrierFree.user.entity.User;
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
public class FavoritePlace extends BaseEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private Long placeId;
    private Long userId;
    private String placename;
    private Double latitude;
    private Double longitude;
}
