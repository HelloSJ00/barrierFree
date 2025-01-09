package com.cloudingYo.barrierFree.user.controller;

import com.cloudingYo.barrierFree.user.dto.UserDTO;
import com.cloudingYo.barrierFree.user.dto.UserResponseDTO;
import com.cloudingYo.barrierFree.user.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/spring/user")
public class UserController{

    private final UserService userService;

    @GetMapping("/sessionCheck")
    public ResponseEntity<String> checkSession(HttpSession session) {
        // 세션이 존재하는지 확인
        if (session == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Session not found");
        }

        Long userId = (Long) session.getAttribute("userId");

        // 세션이 있더라도 userId가 없으면 401 응답
        if (userId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Session expired or userId not set");
        }

        return ResponseEntity.ok("session active");
    }

    @GetMapping("/emailCheck")
    public ResponseEntity<UserResponseDTO<?>> emailCheck(@RequestParam String email) {
        if (userService.isEmailExists(email)){
            return ResponseEntity.ok(UserResponseDTO.fail("이미 존재하는 이메일입니다."));
        }
        else{
            return ResponseEntity.ok(UserResponseDTO.success("사용 가능한 이메일입니다.",true));
        }
    }

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

    @GetMapping("/findUser")
    public ResponseEntity<UserResponseDTO<?>> findUser(HttpSession session) {
        // 세션에서 사용자 ID를 가져옵니다.
        String userEmail = (String) session.getAttribute("userEmail");
        if (userEmail == null) {
            // 세션에 사용자 정보가 없으면 인증되지 않은 상태입니다.
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        // 데이터베이스에서 사용자 정보를 조회합니다.
        UserDTO userInfo = userService.findUser(userEmail);
        if (userInfo == null){
            return ResponseEntity.ok(UserResponseDTO.fail("회원정보를 찾을 수 없습니다."));
        }
        else{
            return ResponseEntity.ok(UserResponseDTO.success("회원정보를 찾았습니다.", userInfo));
        }
    }

    @PutMapping("/userUpdate")
    public ResponseEntity<UserResponseDTO<?>> updateUser(@RequestBody UserDTO userDTO ,HttpSession session) {
        String email = session.getAttribute("userEmail").toString();
        if (userService.updateUser(email,userDTO.getUsername())){
            return ResponseEntity.ok(UserResponseDTO.success("회원정보 수정이 완료되었습니다."));
        }
        else{
            return ResponseEntity.ok(UserResponseDTO.fail("회원정보 수정에 실패했습니다."));
        }
    }

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
