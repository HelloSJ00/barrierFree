package com.cloudingYo.barrierFree.user.controller;

import com.cloudingYo.barrierFree.user.dto.UserDTO;
import com.cloudingYo.barrierFree.user.dto.UserResponseDTO;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

public interface UserController {
    ResponseEntity<String> checkSession(HttpSession session);
    ResponseEntity<UserResponseDTO<?>> emailCheck(@RequestParam String email);
    ResponseEntity<UserResponseDTO<?>> findUser(HttpSession session);
    ResponseEntity<UserResponseDTO<?>> registerUser(@RequestBody UserDTO usersDTO);
    ResponseEntity<UserResponseDTO<?>> updateUser(@RequestBody UserDTO userDTO ,HttpSession session);
    ResponseEntity<UserResponseDTO<?>> deleteUser(@RequestBody UserDTO userDTO);
}
