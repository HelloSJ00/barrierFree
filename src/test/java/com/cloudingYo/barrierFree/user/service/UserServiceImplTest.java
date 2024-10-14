package com.cloudingYo.barrierFree.user.service;

import com.cloudingYo.barrierFree.user.dto.UserDTO;
import com.cloudingYo.barrierFree.user.dto.UserSignupDTO;
import com.cloudingYo.barrierFree.user.entity.User;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Commit
class UserServiceImplTest {
}