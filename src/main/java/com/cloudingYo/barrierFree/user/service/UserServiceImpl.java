package com.cloudingYo.barrierFree.user.service;

import com.cloudingYo.barrierFree.user.dto.UserDTO;
import com.cloudingYo.barrierFree.user.entity.USER_ROLE;
import com.cloudingYo.barrierFree.user.entity.User;
import com.cloudingYo.barrierFree.user.exception.DuplicatedEmailException;
import com.cloudingYo.barrierFree.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional(readOnly = true)
    public UserDTO findUser(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if(user.isEmpty()){
            throw new DuplicatedEmailException("이 이메일은 이미 사용 중입니다.");
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
    public void registerUser(UserDTO userDTO) {
        if(userRepository.findByEmail(userDTO.getEmail()).isPresent()){
            throws new DuplicatedEmailException("이 이메일은 이미 사용 중입니다.");
        }else{
            User user = User.builder()
                    .username(userDTO.getUsername())
                    .email(userDTO.getEmail())
                    .password(passwordEncoder.encode(userDTO.getPassword()))  // 암호화된 비밀번호 저장
                    .role(USER_ROLE.ROLE_USER)
                    .build();
            // 회원가입
            userRepository.save(user);
        }

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
