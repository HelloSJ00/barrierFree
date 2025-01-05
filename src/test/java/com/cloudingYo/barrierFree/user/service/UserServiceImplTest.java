package com.cloudingYo.barrierFree.user.service;

import com.cloudingYo.barrierFree.user.DummyUserRepositoryImpl;
import com.cloudingYo.barrierFree.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Commit;

@SpringBootTest
class UserServiceImplTest {
    @Autowired
    private PasswordEncoder passwordEncoder; // 스프링 컨텍스트에서 주입받음

    private UserService userService;

    @BeforeEach
    void 리포지토리는_더미_대역으로() {
        userService = new UserServiceImpl(new DummyUserRepositoryImpl(), passwordEncoder);
    }



}