package com.cloudingYo.barrierFree.user.controller;

import com.cloudingYo.barrierFree.user.dto.UserDTO;
import com.cloudingYo.barrierFree.user.dto.UserResponseDTO;
import com.cloudingYo.barrierFree.user.entity.User;
import com.cloudingYo.barrierFree.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserControllerImpl implements UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Override
    @PostMapping("/register")
    public String registerUser(@RequestBody UserDTO userDTO) {
        if (userService.isEmailExists(userDTO)){
            System.out.println("이미 존재하는 이메일입니다.");
            return "이미 존재하는 이메일입니다.";
        }
        else{
            userService.registerUser(userDTO);
            System.out.println("회원가입이 완료되었습니다.");
            return "회원가입이 완료되었습니다.";
        }
    }

    @Override
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDTO userDTO) {
        // userService.login() 에서 passwordEncoder.encode()를 사용하여 로그인
        User user = userService.login(userDTO.getEmail(), userDTO.getPassword());

        if (user == null){
            System.out.println("로그인 실패");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인 실패: 이메일 또는 비밀번호가 잘못되었습니다.");
        }

        UserResponseDTO userResponseDTO = new UserResponseDTO(user.getId(),user.getUsername(), user.getEmail());
        return ResponseEntity.ok(userResponseDTO);
    }

    @Override
    @PutMapping("/userUpdate")
    public String updateUser(@RequestBody UserDTO userDTO) {
        if (userService.updateUser(userDTO)){
            return "회원정보가 수정되었습니다.";
        }
        else{
            return "회원정보 수정에 실패했습니다.";
        }
    }

    @Override
    @DeleteMapping("/deleteUser")
    public String deleteUser(@RequestBody UserDTO userDTO) {
        if (userService.deleteUser(userDTO)){
            return "회원탈퇴가 완료되었습니다.";
        }
        else{
            return "회원탈퇴에 실패했습니다.";
        }
    }
}
