package com.cloudingYo.barrierFree.user;

import com.cloudingYo.barrierFree.user.entity.User;
import com.cloudingYo.barrierFree.user.repository.UserRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

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
    public Optional<User> findByEmail(String email) {
        return database.values().stream()
                .filter(user -> user.getEmail().equals(email)) // 이메일이 일치하는 유저 필터링
                .findFirst(); // 첫 번째 일치하는 유저를 Optional로 반환
    }

    @Override
    public boolean existsByEmail(String email) {
        return false;
    }
}
