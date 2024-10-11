package com.cloudingYo.barrierFree.user.service;

import com.cloudingYo.barrierFree.user.dto.UserDTO;
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
        UserDTO userDTO = UserDTO.builder()
                .email(email)
                .build();

        // when
        boolean result = userService.isEmailExists(userDTO);

        // then
        assertTrue(result);
        System.out.println("이메일 중복확인 테스트 끝");
    }

    @Test
    @Transactional
    @Rollback(false)
    void 유저생성_탈퇴() {
        // given
        UserDTO userDTO = UserDTO.builder()
                .username("test")
                .email("test@test.com")
                .password("test")
                .build();

        // when
        userService.registerUser(userDTO);

        em.flush();
        em.clear();

        boolean result = userService.isEmailExists(userDTO);  // 메서드 이름 변경
        org.assertj.core.api.Assertions.assertThat(result).isTrue();

        // when
        User user = userService.login(userDTO.getEmail(), userDTO.getPassword());
        // then
        assertEquals(user.getUsername(), userDTO.getUsername());
        assertEquals(user.getEmail(), userDTO.getEmail());
        assertTrue(passwordEncoder.matches(userDTO.getPassword(), user.getPassword()));  // 암호화된 비밀번호 비교
        System.out.println("유저 생성 테스트 끝");

        // when
        userService.deleteUser(userDTO);

        // flush와 clear를 호출하여 데이터베이스 상태를 갱신
        em.flush();
        em.clear();

        boolean deleteResult = userService.isEmailExists(userDTO);  // 이메일 존재 여부 재확인
        org.assertj.core.api.Assertions.assertThat(deleteResult).isFalse();
    }





    @Test
    @Transactional
    void 로그인() {
        // given
        UserDTO userDTO = UserDTO.builder()
                .username("test")
                .email("test@test1.com")
                .password("test")
                .build();
        userService.registerUser(userDTO);
        em.flush();
        em.clear();
        // when
        User loginUser = userService.login(userDTO.getEmail(), userDTO.getPassword());


        // then
        assertNotNull(loginUser);
        System.out.println("로그인 테스트 끝");
    }
}