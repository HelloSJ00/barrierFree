package com.cloudingYo.barrierFree.user.repository;

import com.cloudingYo.barrierFree.user.entity.User;

public interface UserRepository {
    User save(User user);
    void delete(User user);
    User findByEmail(String email);
    boolean existsByEmail(String email);
}