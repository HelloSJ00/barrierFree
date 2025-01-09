package com.cloudingYo.barrierFree.user.service;

import com.cloudingYo.barrierFree.common.exception.model.CustomException;
import com.cloudingYo.barrierFree.user.dto.req.UserDTO;
import com.cloudingYo.barrierFree.user.entity.USER_ROLE;
import com.cloudingYo.barrierFree.user.entity.User;
import com.cloudingYo.barrierFree.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.cloudingYo.barrierFree.common.exception.enums.ErrorType.NOT_FOUND_USER_INFORMATION;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    /*
     * 기본
     */
    @Override
    @Transactional(readOnly = true)
    public UserDTO findUser(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isEmpty()) {
            throw new CustomException(NOT_FOUND_USER_INFORMATION);
        } else {
            return user.map(value -> UserDTO.builder()
                    .username(value.getUsername())
                    .email(value.getEmail())
                    .build()).orElse(null);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public boolean isEmailExists(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public UserDTO registerUser(UserDTO userDTO) {
        if (userRepository.findByEmail(userDTO.getEmail()).isPresent()) {
            throw new CustomException(NOT_FOUND_USER_INFORMATION);
        } else {
            User user = User.builder()
                    .username(userDTO.getUsername())
                    .email(userDTO.getEmail())
                    .password(passwordEncoder.encode(userDTO.getPassword()))  // 암호화된 비밀번호 저장
                    .role(USER_ROLE.ROLE_USER)
                    .build();
            // 회원가입
            User save = userRepository.save(user);
            return UserDTO.builder()
                    .email(save.getEmail())
                    .username(save.getUsername())
                    .build();
        }

    }

    @Override
    public boolean updateUser(String email, String updateUsername) {
        Optional<User> existingUser = userRepository.findByEmail(email);
        if (existingUser.isEmpty()) {
            throw new CustomException(NOT_FOUND_USER_INFORMATION);
        }
        User findUser = existingUser.get();
        findUser.updateUsername(updateUsername);
        // 회원정보 수정
        userRepository.save(findUser);
        return true;
    }


    @Override
    public boolean deleteUser(UserDTO userDTO) {
        Optional<User> deleteUser = userRepository.findByEmail(userDTO.getEmail());
        // 탈퇴
        if (deleteUser.isEmpty()) {
//            throw new NotExistUserException("등록되지 않은 이메일입니다.");
            throw new CustomException(NOT_FOUND_USER_INFORMATION);
        }
        userRepository.delete(deleteUser.get());
        return true;
    }
}
