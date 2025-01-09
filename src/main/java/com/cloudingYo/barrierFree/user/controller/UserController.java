package com.cloudingYo.barrierFree.user.controller;

import com.cloudingYo.barrierFree.common.entity.ApiResponse;
import com.cloudingYo.barrierFree.user.dto.UserDTO;
import com.cloudingYo.barrierFree.user.dto.UserResponseDTO;
import com.cloudingYo.barrierFree.user.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.cloudingYo.barrierFree.common.exception.enums.SuccessType.*;

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
    public ResponseEntity<ApiResponse<?>> emailCheck(@RequestParam String email) {
        return ResponseEntity.ok(ApiResponse.success(EMAIL_AVAILABLE,userService.isEmailExists(email)));
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<?>> registerUser(@RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(ApiResponse.success(SIGNUP_SUCCESS,userService.registerUser(userDTO)));
    }

    @GetMapping("/findUser")
    public ResponseEntity<ApiResponse<?>> findUser(HttpSession session) {
        // 세션에서 사용자 ID를 가져옵니다.
        String userEmail = (String) session.getAttribute("userEmail");
        return ResponseEntity.ok(ApiResponse.success(FIND_USER_INFORMATION,userService.findUser(userEmail)));
    }

    @PutMapping("/userUpdate")
    public ResponseEntity<ApiResponse<?>> updateUser(@RequestBody UserDTO userDTO ,HttpSession session) {
        String email = (String)session.getAttribute("userEmail");
        return ResponseEntity.ok(ApiResponse.success(UPDATE_SUCCESS_USER_INFORMATION,userService.updateUser(email, userDTO.getUsername())));
    }

    @DeleteMapping("/deleteUser")
    public ResponseEntity<ApiResponse<?>> deleteUser(@RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(ApiResponse.success(SIGN_OUT_SUCCESS,userService.deleteUser(userDTO)));
    }
}
