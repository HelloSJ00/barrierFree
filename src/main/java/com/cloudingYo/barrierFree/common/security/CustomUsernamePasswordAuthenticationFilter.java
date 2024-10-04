package com.cloudingYo.barrierFree.common.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

public class CustomUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public CustomUsernamePasswordAuthenticationFilter(AuthenticationManager authenticationManager) {
        super.setAuthenticationManager(authenticationManager);
        setFilterProcessesUrl("/user/login");  // 필터가 처리할 경로를 '/user/login'으로 설정
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        // POST 요청인지와 Content-Type이 JSON인지 확인
        if ("POST".equalsIgnoreCase(request.getMethod()) && request.getContentType() != null && request.getContentType().contains("application/json")) {
            try {
                // JSON 요청에서 이메일과 비밀번호 추출
                Map<String, String> credentials = objectMapper.readValue(request.getInputStream(), Map.class);
                String username = credentials.get("email");
                String password = credentials.get("password");

                // 이메일이나 비밀번호가 없으면 오류 반환
                if (username == null || password == null) {
                    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    response.getWriter().write("Email or password is missing");
                    return null;
                }

                // 인증 요청 객체 생성 및 인증 처리
                UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);
                return this.getAuthenticationManager().authenticate(authRequest);
            } catch (IOException e) {
                // JSON 처리 실패 시 에러 반환
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                try {
                    response.getWriter().write("Invalid request body");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                return null;
            }
        }

        // JSON이 아닌 요청은 상위 클래스의 기본 동작으로 처리
        return super.attemptAuthentication(request, response);
    }
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        // 인증 성공 시, 인증 정보를 SecurityContext에 저장
        SecurityContextHolder.getContext().setAuthentication(authResult);

        // 세션 생성 및 관리
        HttpSession session = request.getSession(true);
        session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());

        // 인증된 사용자 정보 추출
        String username = authResult.getName(); // 인증된 사용자 이름 (보통 이메일이나 유저명)
        Collection<? extends GrantedAuthority> roles = authResult.getAuthorities(); // 권한 정보

        // 원하는 JSON 응답 생성
        String jsonResponse = String.format("{\"message\": \"Login successful\", \"user\": \"%s\", \"roles\": \"%s\"}",
                username,
                roles.stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(", ")));

        // 응답 처리
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().write(jsonResponse);
    }


    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        // 인증 실패 시 403 에러 반환
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.getWriter().write("Authentication Failed: " + failed.getMessage());
    }


}
