package com.cloudingYo.barrierFree.user.service;

import com.cloudingYo.barrierFree.user.dto.UserDTO;
import com.cloudingYo.barrierFree.user.dto.UserSignupDTO;
import com.cloudingYo.barrierFree.user.entity.User;

public interface UserService {

    // 기본
    User createUser(String username,String email, String password);
    UserDTO findUser(String email);

    // 회원가입
    boolean isEmailExists(String email);
    void registerUser(UserSignupDTO userSignupDTO);

    // 로그인
    User login(String email, String password);

    //회원정보 수정
    boolean updateUser(UserDTO userDTO);

    //탈퇴
    boolean deleteUser(UserDTO userDTO);
}
