package com.cloudingYo.barrierFree.user.service;

import com.cloudingYo.barrierFree.user.dto.resp.CustomUserDetails;
import com.cloudingYo.barrierFree.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    /*
    * 이메일로 로그인하는데 시큐리티에서는 username으로 로그인하기 때문에
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new CustomUserDetails(userRepository.findByEmail(username));
    }
}
