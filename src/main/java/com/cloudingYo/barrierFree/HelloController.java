package com.cloudingYo.barrierFree;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")  // 모든 도메인에서 요청 허용
public class HelloController {

    @GetMapping("/")
    public String index() {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Iterator<? extends GrantedAuthority> iter = authorities.iterator();
        GrantedAuthority auth = iter.next();
        String role = auth.getAuthority();
        // name과 role을 Map에 담아 JSON으로 반환
        Map<String, String> response = new HashMap<>();
        response.put("name", name);
        response.put("role", role);

        return response.toString();
    }

    @GetMapping("/api/hello")
    public Map<String, String> sayHello() {
        Map<String, String> response = new HashMap<>();
        response.put("message", "연결 테스트 중입니다!");
        return response;
    }
}
