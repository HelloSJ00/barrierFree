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
    @Transactional(readOnly = true)
    public User login(String email, String password) {
        // 이메일로 유저를 찾음
        Optional<User> existingUser = userRepository.findByEmail(email);

        // 유저가 존재하지 않으면 예외를 던짐
        if (existingUser.isEmpty()) {
            throw new IllegalArgumentException("유저를 찾을 수 없습니다.");
        }

        User user = existingUser.get();

        // 디버깅을 위한 로그 출력
        System.out.println("입력된 비밀번호: " + password);
        System.out.println("저장된 암호화된 비밀번호: " + user.getPassword());

        // 입력된 비밀번호와 저장된 암호화된 비밀번호를 비교
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        // 유저가 존재하고 비밀번호가 일치하면 유저 객체 반환
        return user;
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
