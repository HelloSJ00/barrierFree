package com.cloudingYo.barrierFree.user;

import com.cloudingYo.barrierFree.user.entity.User;
import com.cloudingYo.barrierFree.user.repository.UserRepository;

import java.util.Optional;

public class DummyUserRepositoryImpl implements UserRepository {
    @Override
    public User save(User user) {
        return null;
    }

    @Override
    public void delete(User user) {

    }

    @Override
    public Optional<User> findByEmail(String email) {
        return Optional.empty();
    }

    @Override
    public boolean existsByEmail(String email) {
        return false;
    }
}
