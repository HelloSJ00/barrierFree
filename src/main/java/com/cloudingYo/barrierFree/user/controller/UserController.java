package com.cloudingYo.barrierFree.user.controller;

import com.cloudingYo.barrierFree.user.dto.UserDTO;
import com.cloudingYo.barrierFree.user.dto.UserResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface UserController {

    String registerUser(@RequestBody UserDTO userDTO);
    ResponseEntity<?> login(@RequestBody UserDTO userDTO);
    String updateUser(@RequestBody UserDTO userDTO);
    String deleteUser(@RequestBody UserDTO userDTO);
}
