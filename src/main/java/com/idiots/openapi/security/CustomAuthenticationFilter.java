package com.idiots.openapi.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Data;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;

public class CustomAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    private ObjectMapper objectMapper = new ObjectMapper();

    public CustomAuthenticationFilter() {
        // url과 일치할 경우 해당 필터가 동작합니다.
        super(new AntPathRequestMatcher("/login"));
    }
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException, IOException, ServletException {

        // 해당 요청이 POST 인지 확인
        if(!isPost(request)) {
            throw new IllegalStateException("Authentication is not supported");
        }

        // POST 이면 body 를 LoginDto( 로그인 요청 DTO ) 에 매핑
        LoginDto loginDto = objectMapper.readValue(request.getReader(), LoginDto.class);

        // ID, PASSWORD 가 있는지 확인
        if(!StringUtils.hasLength(loginDto.getUsername())
                || !StringUtils.hasLength(loginDto.getPassword())) {
            throw new IllegalArgumentException("username or password is empty");
        }

        // 처음에는 인증 되지 않은 토큰 생성
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                loginDto.getUsername(),
                loginDto.getPassword()
        );

        // AuthenticationManager 에게 인증 처리
        Authentication authenticate = getAuthenticationManager().authenticate(token);

        return authenticate;
    }

    private boolean isPost(HttpServletRequest request) {
        if("POST".equals(request.getMethod())) {
            return true;
        }

        return false;
    }


    @Data
    public static class LoginDto {
        private String username;
        private String password;
    }

}
