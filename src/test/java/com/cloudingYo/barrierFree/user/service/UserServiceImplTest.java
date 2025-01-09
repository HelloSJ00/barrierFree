package com.cloudingYo.barrierFree.user.service;

import com.cloudingYo.barrierFree.common.exception.model.CustomException;
import com.cloudingYo.barrierFree.user.DummyPasswordEncoder;
import com.cloudingYo.barrierFree.user.dto.req.UserDTO;
import com.cloudingYo.barrierFree.user.repository.StubEmptyUserRepositoryImpl;
import com.cloudingYo.barrierFree.user.repository.StubExistUserRepositoryImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

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
        org.junit.jupiter.api.Assertions.assertThrows(CustomException.class,()->{
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
        String email = "test@test.com";
        //when
        UserServiceImpl userService = new UserServiceImpl(new StubEmptyUserRepositoryImpl(),new DummyPasswordEncoder());
        //then
        Assertions.assertThat(userService.isEmailExists(email)).isFalse();
    }

    @Test
    void 가입_가능한_이메일이다(){
        //givrn
        String email = "test@test.com";
        //when
        UserServiceImpl userService = new UserServiceImpl(new StubExistUserRepositoryImpl(),new DummyPasswordEncoder());
        //then
        Assertions.assertThat(userService.isEmailExists(email)).isTrue();
    }

    /*
    void registerUser(UserDTO userDTO);
     */
    @Test
    void 중복_이메일로_가입_시도시_예외_처리(){
        //givrn
        UserDTO userDTO = UserDTO.builder()
                .username("test")
                .email("test@test.com")
                .password("test")
                .build();
        UserServiceImpl userService = new UserServiceImpl(new StubExistUserRepositoryImpl(),new DummyPasswordEncoder());

        //then
        org.junit.jupiter.api.Assertions.assertThrows(CustomException.class,()->{
            //when
            userService.registerUser(userDTO);
        });
    }

    @Test
    void 가입_가능한_이메일로_가입_시도시_성공(){
        //givrn
        UserDTO userDTO = UserDTO.builder()
                .username("test")
                .email("test@test.com")
                .password("test")
                .build();
        UserServiceImpl userService = new UserServiceImpl(new StubEmptyUserRepositoryImpl(),new DummyPasswordEncoder());

        //then
        Assertions.assertThat(userService.registerUser(userDTO).getEmail()).isEqualTo(userDTO.getEmail());
    }

    /*
    boolean updateUser(String email,String updateUsername);
     */
    @Test
    void 회원은_회원_닉네임을_변경_가능하다(){
        //givrn
        String email = "test@test.com";
        String updateUsername = "test";
        UserServiceImpl userService = new UserServiceImpl(new StubExistUserRepositoryImpl(),new DummyPasswordEncoder());

        //then
        Assertions.assertThat(
                //when
                userService.updateUser(email,updateUsername))
                .isTrue();
    }

    @Test
    void 회원이_존재하지_않을시_예외(){
        //givrn
        String email = "test@test.com";
        String updateUsername = "test";
        UserServiceImpl userService = new UserServiceImpl(new StubEmptyUserRepositoryImpl(),new DummyPasswordEncoder());
        //then
        org.junit.jupiter.api.Assertions.assertThrows(CustomException.class,()->{
            userService.updateUser(email,updateUsername);
        });
    }

    /*
    boolean deleteUser(UserDTO userDTO);
     */
    @Test
    void 회원이_아닌_사용자는_탈퇴시_예외_처리(){
        //givrn
        UserDTO userDTO = UserDTO.builder()
                .email("test@test.com")
                .build();
        UserServiceImpl userService = new UserServiceImpl(new StubEmptyUserRepositoryImpl(),new DummyPasswordEncoder());

        //then
        org.junit.jupiter.api.Assertions.assertThrows(CustomException.class,()->{
           userService.deleteUser(userDTO);
        });
    }

    @Test
    void 회원은_회원_탈퇴가_가능하다(){
        //givrn
        UserDTO userDTO = UserDTO.builder()
                .email("test@test.com")
                .build();
        UserServiceImpl userService = new UserServiceImpl(new StubExistUserRepositoryImpl(),new DummyPasswordEncoder());

        //then
        Assertions.assertThat(
                //when
                userService.deleteUser(userDTO)
        ).isTrue();
    }
}