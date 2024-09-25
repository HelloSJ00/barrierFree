package com.cloudingYo.barrierFree.infra.entity;

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
public class Infra {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "infra_id")
    private Long id;
    private String infraname;
}
