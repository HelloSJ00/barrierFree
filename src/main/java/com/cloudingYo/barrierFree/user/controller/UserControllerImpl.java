package com.cloudingYo.barrierFree.user.controller;

import com.cloudingYo.barrierFree.common.entity.ApiResponse;
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
    @GetMapping("/emailCheck")
    public ResponseEntity<UserResponseDTO<?>> emailCheck(@RequestParam String email) {
        if (userService.isEmailExists(email)){
            return ResponseEntity.ok(UserResponseDTO.fail("이미 존재하는 이메일입니다."));
        }
        else{
            return ResponseEntity.ok(UserResponseDTO.success("사용 가능한 이메일입니다.",true));
        }
    }
    @Override
    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO<?>> registerUser(@RequestBody UserDTO userDTO) {
        if (userService.isEmailExists(userDTO.getEmail())){
            return ResponseEntity.ok(UserResponseDTO.fail("이미 존재하는 이메일입니다."));
        }
        else{
            userService.registerUser(userDTO);
            return ResponseEntity.ok(UserResponseDTO.success("회원가입이 완료되었습니다."));
        }
    }


    @Override
    @GetMapping("/findUser")
    public ResponseEntity<UserResponseDTO<?>> findUser(@RequestParam String email) {
        UserDTO findUser = userService.findUser(email);
        if (findUser == null){
            return ResponseEntity.ok(UserResponseDTO.fail("회원정보를 찾을 수 없습니다."));
        }
        else{
            return ResponseEntity.ok(UserResponseDTO.success("회원정보를 찾았습니다.", findUser));
        }
    }

    @Override
    @PostMapping("/login")
    public ResponseEntity<UserResponseDTO<?>> login(@RequestBody UserDTO userDTO) {
        User user = userService.login(userDTO.getEmail(), userDTO.getPassword());
        if (user == null){
            return ResponseEntity.ok(UserResponseDTO.fail("로그인에 실패했습니다."));
        }
        else{
            UserDTO findUserDTO = UserDTO.builder()
                    .username(user.getUsername())
                    .email(user.getEmail())
                    .build();
            return ResponseEntity.ok(UserResponseDTO.success("로그인에 성공했습니다.", findUserDTO));
        }
    }

    @Override
    @PutMapping("/userUpdate")
    public ResponseEntity<UserResponseDTO<?>> updateUser(@RequestBody UserDTO userDTO) {
        if (userService.updateUser(userDTO)){
            return ResponseEntity.ok(UserResponseDTO.success("회원정보 수정이 완료되었습니다."));
        }
        else{
            return ResponseEntity.ok(UserResponseDTO.fail("회원정보 수정에 실패했습니다."));
        }
    }

    @Override
    @DeleteMapping("/deleteUser")
    public ResponseEntity<UserResponseDTO<?>> deleteUser(@RequestBody UserDTO userDTO) {
        if (userService.deleteUser(userDTO)){
            return ResponseEntity.ok(UserResponseDTO.success("회원탈퇴가 완료되었습니다."));
        }
        else{
            return ResponseEntity.ok(UserResponseDTO.fail("회원탈퇴에 실패했습니다."));
        }
    }
}
