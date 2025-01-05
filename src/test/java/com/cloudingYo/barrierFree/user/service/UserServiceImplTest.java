package com.cloudingYo.barrierFree.user.service;

import com.cloudingYo.barrierFree.user.DummyPasswordEncoder;
import com.cloudingYo.barrierFree.user.FakeUserRepositoryImpl;
import com.cloudingYo.barrierFree.user.StubEmptyUserRepositoryImpl;
import com.cloudingYo.barrierFree.user.StubExistUserRepositoryImpl;
import com.cloudingYo.barrierFree.user.dto.UserDTO;
import com.cloudingYo.barrierFree.user.entity.USER_ROLE;
import com.cloudingYo.barrierFree.user.entity.User;
import com.cloudingYo.barrierFree.user.exception.DuplicatedEmailException;
import com.cloudingYo.barrierFree.user.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Commit;

class UserServiceImplTest {
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
        String email = "test@test.com";

        //when
        UserServiceImpl userService = new UserServiceImpl(new StubExistUserRepositoryImpl(),new DummyPasswordEncoder());
        UserDTO userDTO = userService.findUser(email);
        //then
        Assertions.assertThat(userDTO.getEmail()).isEqualTo(email);
    }

    @Test
    void 없는_메일로_유저_조회시_오류(){
        //givrn
        String email = "test@test.com";
        UserServiceImpl userService = new UserServiceImpl(new StubEmptyUserRepositoryImpl(),new DummyPasswordEncoder());

        //then
        org.junit.jupiter.api.Assertions.assertThrows(DuplicatedEmailException.class,()->{
           //when
           userService.findUser(email);
        });
    }

    /*
     boolean isEmailExists(String email);
     */
    @Test
    void 이미_가입한_이메일이다(){
        //givrn

        //when

        //then
    }

    @Test
    void 가입_가능한_이메일이다(){
        //givrn

        //when

        //then
    }
}