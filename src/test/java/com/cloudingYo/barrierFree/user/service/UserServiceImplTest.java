package com.cloudingYo.barrierFree.user.service;

import com.cloudingYo.barrierFree.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

@SpringBootTest
class UserServiceImplTest {

    @Autowired
    UserService userService;

}