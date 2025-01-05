package com.cloudingYo.barrierFree.user.service;

import com.cloudingYo.barrierFree.user.DummyPasswordEncoder;
import com.cloudingYo.barrierFree.user.DummyUserRepositoryImpl;
import com.cloudingYo.barrierFree.user.FakeUserRepositoryImpl;
import com.cloudingYo.barrierFree.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Commit;

@SpringBootTest
class UserServiceImplTest {
    private UserService userService;
    @BeforeEach
    void 리포지토리는_더미_대역으로() {
        userService = new UserServiceImpl(new FakeUserRepositoryImpl(), new DummyPasswordEncoder());
    }
    /*
     * 테스트 항목
     * // 기본
        UserDTO findUser(String email);

        // 회원가입
        boolean isEmailExists(String email);
        void registerUser(UserDTO userDTO);

        //회원정보 수정
        boolean updateUser(String email,String updateUsername);

        //탈퇴
        boolean deleteUser(UserDTO userDTO);
     */


    /*
    UserDTO findUser(String email);
     */
    @Test
    void 이메일로_유저를_찾을_수_있다(){
        //givrn

        //when

        //then
    }

    @Test
    void 없는_메일로_유저_조회시_오류(){
        //givrn

        //when

        //then
    }
}