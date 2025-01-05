package com.cloudingYo.barrierFree.user.repository;

import com.cloudingYo.barrierFree.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository {
    User save(User user);
    void delete(User user);
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
}