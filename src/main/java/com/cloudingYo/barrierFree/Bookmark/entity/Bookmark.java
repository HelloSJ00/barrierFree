package com.cloudingYo.barrierFree.Bookmark.entity;

import com.cloudingYo.barrierFree.common.entity.BaseEntity;
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
public class Bookmark extends BaseEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private Long placeId;
    private Long placeKey;
    private Long userId;
    private String placename;
    private Double latitude;
    private Double longitude;
}
