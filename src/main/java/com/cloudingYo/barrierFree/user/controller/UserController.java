package com.cloudingYo.barrierFree.user.controller;

import com.cloudingYo.barrierFree.common.entity.ApiResponse;
import com.cloudingYo.barrierFree.user.dto.UserDTO;
import com.cloudingYo.barrierFree.user.dto.UserResponseDTO;
import com.cloudingYo.barrierFree.user.dto.UserSignupDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

public interface UserController {

    ResponseEntity<UserResponseDTO<?>> emailCheck(@RequestParam String email);
    ResponseEntity<UserResponseDTO<?>> findUser(@RequestParam String email);
    ResponseEntity<UserResponseDTO<?>> registerUser(@RequestBody UserSignupDTO usersignupDTO);
    ResponseEntity<UserResponseDTO<?>> login(@RequestBody UserDTO userDTO);
    ResponseEntity<UserResponseDTO<?>> updateUser(@RequestBody UserDTO userDTO);
    ResponseEntity<UserResponseDTO<?>> deleteUser(@RequestBody UserDTO userDTO);
}
