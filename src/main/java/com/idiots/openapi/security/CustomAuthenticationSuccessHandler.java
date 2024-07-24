package com.idiots.openapi.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Slf4j
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private RequestCache requestCache = new HttpSessionRequestCache();
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.info("아이디 : {}, 권한 : {}", authentication.getName(), authentication.getAuthorities());
//        resultRedirectStrategy(request, response, authentication);
        clearAuthenticationAttributes(request);
    }

//    private void resultRedirectStrategy(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
//        SavedRequest savedRequest = requestCache.getRequest(request, response);
//        // Security가 요청을 가로챈 경우, 사용자가 원래 요청했던 URI 정보를 저장한 객체
//        if(savedRequest != null) {
//            String targetUrl = savedRequest.getRedirectUrl();
//            System.out.println(targetUrl);
//            requestCache.removeRequest(request, response);
//            redirectStrategy.sendRedirect(request, response, targetUrl);
//        } else {
//            redirectStrategy.sendRedirect(request, response, "/");
//        }
//    }

    // 로그인 실패 시 에러가 세션에 저장됨 -> 성공 시 에러 세션 삭제 작업
    private void clearAuthenticationAttributes(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if(session == null) return;
        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    }
}
