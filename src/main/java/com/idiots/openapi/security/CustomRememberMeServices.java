package com.idiots.openapi.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;

public class CustomRememberMeServices extends TokenBasedRememberMeServices {

    public CustomRememberMeServices(String key, UserDetailsService userDetailsService) {
        super(key, userDetailsService);
    }

    public CustomRememberMeServices(String key, UserDetailsService userDetailsService,
                                    RememberMeTokenAlgorithm encodingAlgorithm) {
        super(key, userDetailsService, encodingAlgorithm);
    }

    public void loginSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication successfulAuthentication,
            boolean rememberMe
    ) {
        if(!this.rememberMeRequested(request, this.getParameter(), rememberMe)) {
            this.logger.debug("Remember-me login not requested.");
        } else {
            this.onLoginSuccess(request, response, successfulAuthentication);
        }
    }

    protected boolean rememberMeRequested(HttpServletRequest request, String parameter, boolean rememberMe) {
        // alwaysRemeber가 true거나 remeberMe가 true라면 동작.
        return super.rememberMeRequested(request, parameter) || rememberMe;
    }
}

