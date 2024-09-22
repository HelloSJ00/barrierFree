package com.cloudingYo.barrierFree.user.entity;

import com.cloudingYo.barrierFree.common.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;
import org.jetbrains.annotations.NotNull;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name="user_id")
    private Long id;

    private String username;
    private String email;
    private String password;
}
