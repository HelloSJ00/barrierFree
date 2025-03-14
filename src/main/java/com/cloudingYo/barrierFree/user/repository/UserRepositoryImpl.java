package com.cloudingYo.barrierFree.user.repository;

import com.cloudingYo.barrierFree.common.exception.model.CustomException;
import com.cloudingYo.barrierFree.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static com.cloudingYo.barrierFree.common.exception.enums.ErrorType.NOT_FOUND_USER_INFORMATION;

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
    public User findByEmail(String email){
        return userJPARepository.findByEmail(email)
                .orElseThrow(()-> new CustomException(NOT_FOUND_USER_INFORMATION));
    }

    @Override
    public boolean existsByEmail(String email){
        return userJPARepository.existsByEmail(email);
    }
}
