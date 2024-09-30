package com.cloudingYo.barrierFree.user.service;

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

    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    EntityManager em;

    @Test
    void 이메일중복확인() {
        // given
        String email = "123@123.com";

        // when
        boolean result = userService.isEmailExists(email);

        // then
        assertTrue(result);
        System.out.println("이메일 중복확인 테스트 끝");
    }

    @Test
    @Transactional
    @Rollback(false)
    void 유저생성_탈퇴() {
        // given
        String username = "test";
        String email = "123@123.com";
        String password = "test";

        // when
        User user = userService.createUser(username, email, password);
        userService.registerUser(user);

        em.flush();
        em.clear();

        boolean result = userService.isEmailExists(email);  // 메서드 이름 변경
        org.assertj.core.api.Assertions.assertThat(result).isTrue();

        // then
        assertEquals(user.getUsername(), username);
        assertEquals(user.getEmail(), email);
        assertTrue(passwordEncoder.matches(password, user.getPassword()));  // 암호화된 비밀번호 비교
        System.out.println("유저 생성 테스트 끝");

        // when
        userService.deleteUser(user);

        // flush와 clear를 호출하여 데이터베이스 상태를 갱신
        em.flush();
        em.clear();

        boolean deleteResult = userService.isEmailExists(email);  // 이메일 존재 여부 재확인
        org.assertj.core.api.Assertions.assertThat(deleteResult).isFalse();
    }





    @Test
    @Transactional
    void 로그인() {
        // given
        String username = "test";
        String email = "123@123.com";
        String password = "test";

        // when
        User user = userService.createUser(username, email, password);
        userService.registerUser(user);
        em.flush();
        em.clear();
        // when
        User loginUser = userService.login(email, password);


        // then
        assertNotNull(loginUser);
        System.out.println("로그인 테스트 끝");
    }
}