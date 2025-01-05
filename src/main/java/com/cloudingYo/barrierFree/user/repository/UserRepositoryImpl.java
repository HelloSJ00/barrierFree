package com.cloudingYo.barrierFree.user.repository;

import com.cloudingYo.barrierFree.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final UserJPARepository userJPARepository;

    @Override
    public User save(User user){
        return userJPARepository.save(user);
    }

    @Override
    public void delete(User user){
        userJPARepository.delete(user);
    }

    @Override
    public Optional<User> findByEmail(String email){
        return userJPARepository.findByEmail(email);
    }

    @Override
    public boolean existsByEmail(String email){
        return userJPARepository.existsByEmail(email);
    }
}
