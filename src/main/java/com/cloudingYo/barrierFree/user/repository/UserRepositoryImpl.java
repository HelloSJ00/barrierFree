package com.cloudingYo.barrierFree.user.repository;

import com.cloudingYo.barrierFree.user.entity.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private final UserJPARepository userJPARepository;

    public UserRepositoryImpl(UserJPARepository userJPARepository) {
        this.userJPARepository = userJPARepository;
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
