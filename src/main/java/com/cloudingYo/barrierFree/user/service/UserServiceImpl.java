package com.cloudingYo.barrierFree.user.service;

import com.cloudingYo.barrierFree.user.dto.req.UserDTO;
import com.cloudingYo.barrierFree.user.entity.USER_ROLE;
import com.cloudingYo.barrierFree.user.entity.User;
import com.cloudingYo.barrierFree.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        User user = userRepository.findByEmail(email);
        return UserDTO.builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .build();
    }

    @Override
    @Transactional(readOnly = true)
    public boolean isEmailExists(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public UserDTO registerUser(UserDTO userDTO) {
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

    @Override
    public boolean updateUser(String email, String updateUsername) {
        User findUser = userRepository.findByEmail(email);
        findUser.updateUsername(updateUsername);
        // 회원정보 수정
        userRepository.save(findUser);
        return true;
    }

    @Override
    public boolean deleteUser(UserDTO userDTO) {
        User deleteUser = userRepository.findByEmail(userDTO.getEmail());
        // 탈퇴
        userRepository.delete(deleteUser);
        return true;
    }
}
