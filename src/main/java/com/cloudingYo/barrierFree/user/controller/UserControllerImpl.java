package com.cloudingYo.barrierFree.user.controller;

import com.cloudingYo.barrierFree.common.entity.ApiResponse;
import com.cloudingYo.barrierFree.user.dto.UserDTO;
import com.cloudingYo.barrierFree.user.dto.UserResponseDTO;
import com.cloudingYo.barrierFree.user.dto.UserSignupDTO;
import com.cloudingYo.barrierFree.user.dto.UserUpdateDTO;
import com.cloudingYo.barrierFree.user.entity.User;
import com.cloudingYo.barrierFree.user.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/spring/user")
public class UserControllerImpl implements UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Override
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
    public ResponseEntity<UserResponseDTO<?>> registerUser(@RequestBody UserSignupDTO userSignupDTO) {
        if (userService.isEmailExists(userSignupDTO.getEmail())){
            return ResponseEntity.ok(UserResponseDTO.fail("이미 존재하는 이메일입니다."));
        }
        else{
            userService.registerUser(userSignupDTO);
            return ResponseEntity.ok(UserResponseDTO.success("회원가입이 완료되었습니다."));
        }
    }


    @Override
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

    @Override
    @PostMapping("/login")
    public ResponseEntity<UserResponseDTO<?>> login(@RequestBody UserDTO userDTO, HttpSession session) {
        User user = userService.login(userDTO.getEmail(), userDTO.getPassword());
        if (user == null){
            return ResponseEntity.ok(UserResponseDTO.fail("로그인에 실패했습니다."));
        } else {
            // 세션에 userEmail과 username 저장
            session.setAttribute("userEmail", user.getEmail());
            session.setAttribute("username", user.getUsername());

            UserDTO findUserDTO = UserDTO.builder()
                    .username(user.getUsername())
                    .email(user.getEmail())
                    .build();
            return ResponseEntity.ok(UserResponseDTO.success("로그인에 성공했습니다.", findUserDTO));
        }
    }


    @Override
    @PutMapping("/userUpdate")
    public ResponseEntity<UserResponseDTO<?>> updateUser(@RequestBody UserUpdateDTO userUpdateDTO ,HttpSession session) {
        String email = session.getAttribute("userEmail").toString();
        if (userService.updateUser(email,userUpdateDTO.getUsername())){
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
