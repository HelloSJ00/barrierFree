package com.cloudingYo.barrierFree.user.repository;

import com.cloudingYo.barrierFree.common.exception.model.CustomException;
import com.cloudingYo.barrierFree.user.entity.User;

import static com.cloudingYo.barrierFree.common.exception.enums.ErrorType.NOT_FOUND_USER_INFORMATION;

public class StubEmptyUserRepositoryImpl implements UserRepository {
    @Override
    public User save(User user) {
        return user;
    }

    @Override
    public void delete(User user) {}

    @Override
    public User findByEmail(String email) {
        throw new CustomException(NOT_FOUND_USER_INFORMATION);
    }

    @Override
    public boolean existsByEmail(String email) {
        return false;
    }
}
