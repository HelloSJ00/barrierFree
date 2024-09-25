package com.cloudingYo.barrierFree.user.repository;

import com.cloudingYo.barrierFree.user.entity.User;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class UserRepositoryTest {

    @Autowired
    EntityManager em ;
    @Autowired
    UserRepository userRepository ;

    @Test
    void findByAll() {
        User userA = User.builder()
                .username("a")
                .email("a")
                .password("a")
                .build();
        User userB = User.builder()
                .username("b")
                .email("b")
                .password("b")
                .build();

        userRepository.save(userA);
        userRepository.save(userB);

        em.flush();
        em.clear();

        List<User> users = userRepository.findAll();  // 모든 유저 찾기
        assertEquals(2, users.size());

        em.flush();
        em.clear();

        User findUser = userRepository.findByEmail("a");  // 이메일로 유저 찾기
        assertEquals("a", findUser.getEmail());
    }


}