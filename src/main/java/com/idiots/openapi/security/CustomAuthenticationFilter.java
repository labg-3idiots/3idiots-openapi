package com.idiots.openapi.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.idiots.openapi.exception.Exception400;
import com.idiots.openapi.exception.user.UserExceptionStatus;
import com.idiots.openapi.utils.ApiUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.RememberMeAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;

@Slf4j
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

            loginExceptionHandler(response, UserExceptionStatus.ID_OR_PASSWORD_NULL);
//            throw new IllegalArgumentException("username or password is empty");
            return null;
        }

        // 처음에는 인증 되지 않은 토큰 생성
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                loginDto.getUsername(),
                loginDto.getPassword()
        );

        // AuthenticationManager 에게 인증 처리
        Authentication authenticate = getAuthenticationManager().authenticate(token);

        // Remember Me 기능 처리
        if ("on".equals(loginDto.getRemember())) {
            RememberMeServices rememberMeServices = getRememberMeServices();
            if (rememberMeServices != null) {
                rememberMeServices.loginSuccess(request, response, authenticate);
            }
        }

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
        private String remember;
    }

    // 로그인 중 Filter에서 오류가 발생했을 때, Exception 처리 값을 클라이언트에게 알려줌
    public void loginExceptionHandler(HttpServletResponse response,  UserExceptionStatus error) {
        response.setStatus(error.getStatus());
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        try {
            String json = new ObjectMapper().writeValueAsString(ResponseEntity.ok(ApiUtils.error(error.getMessage(), error.getStatus(), error.getErrorCode())));
            response.getWriter().write(json);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

}
