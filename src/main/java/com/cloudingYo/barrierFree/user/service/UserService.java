package com.cloudingYo.barrierFree.user.service;

import com.cloudingYo.barrierFree.user.dto.UserDTO;

public interface UserService {

    // 기본
    UserDTO findUser(String email);

    // 회원가입
    boolean isEmailExists(String email);
    void registerUser(UserDTO userDTO);

    //회원정보 수정
    boolean updateUser(String email,String updateUsername);

    //탈퇴
    boolean deleteUser(UserDTO userDTO);
}
