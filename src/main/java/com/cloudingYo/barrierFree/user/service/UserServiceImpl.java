package com.cloudingYo.barrierFree.user.service;

import com.cloudingYo.barrierFree.user.dto.UserDTO;
import com.cloudingYo.barrierFree.user.dto.UserSignupDTO;
import com.cloudingYo.barrierFree.user.dto.UserUpdateDTO;
import com.cloudingYo.barrierFree.user.entity.User;
import com.cloudingYo.barrierFree.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

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
    public User createUser(String username,String email, String password) {
        // 회원가입
        User newUser = User.builder()
                .username(username)
                .email(email)
                .password(passwordEncoder.encode(password))  // 암호화된 비밀번호 저장
                .build();

        return newUser;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDTO findUser(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        return user.map(value -> UserDTO.builder()
                .username(value.getUsername())
                .email(value.getEmail())
                .build()).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean isEmailExists(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public void registerUser(UserSignupDTO userSignupDTO) {
        User user = User.builder()
                .username(userSignupDTO.getUsername())
                .email(userSignupDTO.getEmail())
                .password(passwordEncoder.encode(userSignupDTO.getPassword()))  // 암호화된 비밀번호 저장
                .role("ROLE_USER")
                .build();
        // 회원가입
        userRepository.save(user);
    }

    @Override
    public boolean updateUser(String email,String updateUsername){
        Optional<User> existingUser = userRepository.findByEmail(email);
        if (existingUser.isEmpty()) {
            return false;
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
            return false;
        }
        userRepository.delete(deleteUser.get());
        return true;
    }
}
