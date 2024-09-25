package com.cloudingYo.barrierFree.user.repository;

import com.cloudingYo.barrierFree.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
