package com.idiots.openapi.config;

import jakarta.servlet.http.HttpSession;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Slf4j
@Configuration
public class SecutiryConfig {

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/","/login").permitAll()
                        .anyRequest().authenticated()
                );
//                .authorizeHttpRequests(authorizeRequest ->
//                        authorizeRequest
//                                .requestMatchers(
//                                        AntPathRequestMatcher.antMatcher("/").permitAll()
//                                ).hasAnyRole().authenticated()
//                );
        http
                .formLogin(formLogin ->
                        formLogin
//                                .loginPage("/login")
                                .usernameParameter("id")
                                .passwordParameter("password")
                                .successHandler((request, response, authentication) -> {
                                    log.debug("authentication : " + authentication.getName());
                                    response.sendRedirect("/");
                                })
                                .failureHandler((request, response, exception) -> {
                                    log.debug("exception : " + exception.getMessage());
                                    response.sendRedirect("/");
                                })
                                .permitAll()
                );
        http
                .logout(logout -> logout
                        .logoutUrl("/")
                        .addLogoutHandler((request, response, authentication) -> {
                            HttpSession session = request.getSession();
                            if(session != null) session.invalidate();
                                })
                        .logoutSuccessHandler((request, response, authentication) -> {
                            response.sendRedirect("/");
                        })
                );
        http
                .rememberMe(remember -> remember
                        .tokenValiditySeconds(3600)//만료시간
                );
        return http.build();
    }


}
