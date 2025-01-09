package com.cloudingYo.barrierFree.user.repository;

import com.cloudingYo.barrierFree.user.entity.User;

import java.util.HashMap;
import java.util.Map;

public class FakeUserRepositoryImpl implements UserRepository {
    private final Map<Long, User> database = new HashMap<>();
    private Long idCounter = 1L;

    @Override
    public User save(User user) {
        database.put(user.getId(), user);
        return user;
    }

    @Override
    public void delete(User user) {
        database.remove(user.getId());
    }

    @Override
    public User findByEmail(String email) {
        return null;
    }

    @Override
    public boolean existsByEmail(String email) {
        return database.values().stream()
                .anyMatch(user->user.getEmail().equals(email));
    }
}
