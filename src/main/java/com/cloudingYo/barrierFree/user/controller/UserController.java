package com.cloudingYo.barrierFree.user.controller;

import com.cloudingYo.barrierFree.common.entity.ApiResponse;
import com.cloudingYo.barrierFree.user.dto.req.UserDTO;
import com.cloudingYo.barrierFree.user.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.cloudingYo.barrierFree.common.exception.enums.SuccessType.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/spring/user")
public class UserController {

    private final UserService userService;

    /*
        회원 가입 시 이메일 중복 사용 여부 확인 용도
     */
    @GetMapping("/emailCheck")
    public ResponseEntity<ApiResponse<?>> emailCheck(@RequestParam String email) {
        return ResponseEntity.ok(ApiResponse.success(EMAIL_AVAILABLE, userService.isEmailExists(email)));
    }

    /*
     * 회원 등록
     */
    @PostMapping
    public ResponseEntity<ApiResponse<?>> registerUser(@RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(ApiResponse.success(SIGNUP_SUCCESS, userService.registerUser(userDTO)));
    }

    /*
     * isEmailExist와 차이는 로그인 전후에 사용하는 차이, 세션의 유무
     */
    @GetMapping
    public ResponseEntity<ApiResponse<?>> findUser(HttpSession session) {
        // 세션에서 사용자 ID를 가져옵니다.
        String userEmail = (String) session.getAttribute("userEmail");
        return ResponseEntity.ok(ApiResponse.success(FIND_USER_INFORMATION, userService.findUser(userEmail)));
    }

    /*
        회원 정보 수정
     */
    @PutMapping
    public ResponseEntity<ApiResponse<?>> updateUser(@RequestBody UserDTO userDTO, HttpSession session) {
        String email = (String) session.getAttribute("userEmail");
        return ResponseEntity.ok(ApiResponse.success(UPDATE_SUCCESS_USER_INFORMATION, userService.updateUser(email, userDTO.getUsername())));
    }

    /*
        회원 탈퇴
     */
    @DeleteMapping
    public ResponseEntity<ApiResponse<?>> deleteUser(@RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(ApiResponse.success(SIGN_OUT_SUCCESS, userService.deleteUser(userDTO)));
    }
}
