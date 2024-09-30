package com.cloudingYo.barrierFree.user.service;

import com.cloudingYo.barrierFree.user.dto.UserDTO;
import com.cloudingYo.barrierFree.user.entity.User;

public interface UserService {

    // 기본
    User createUser(String username,String email, String password);

    // 회원가입
    boolean isEmailExists(UserDTO userDTO);
    void registerUser(UserDTO userDTO);

    // 로그인
    User login(String email, String password);

    //회원정보 수정
    boolean updateUser(UserDTO userDTO);

    //탈퇴
    boolean deleteUser(UserDTO userDTO);
}
