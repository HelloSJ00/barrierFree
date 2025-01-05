package com.cloudingYo.barrierFree.user.entity;

import com.cloudingYo.barrierFree.common.entity.BaseEntity;
import jakarta.persistence.*;
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

    @Column(unique = true)
    private String username;
    @Column(unique = true)
    private String email;
    private String password;

    @Enumerated(EnumType.STRING) // JPA에 enum을 문자열로 저장하도록 설정
    private USER_ROLE role;

    public User(String username) {
        this.username = username;
    }

    public void updateUsername(String username) {
        this.username = username;
    }
}
