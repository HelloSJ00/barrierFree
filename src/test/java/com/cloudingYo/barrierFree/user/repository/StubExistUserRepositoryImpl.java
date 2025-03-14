package com.cloudingYo.barrierFree.user.repository;

import com.cloudingYo.barrierFree.user.entity.User;

public class StubExistUserRepositoryImpl implements UserRepository {
    @Override
    public User save(User user) {
        return user;
    }

    @Override
    public void delete(User user) {

    }

    @Override
    public User findByEmail(String email) {
        return User.builder()
                .email(email)
                .build();
    }

    @Override
    public boolean existsByEmail(String email) {
        return true;
    }
}
