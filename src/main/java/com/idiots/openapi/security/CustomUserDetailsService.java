package com.idiots.openapi.security;

import com.idiots.openapi.exception.Exception404;
import com.idiots.openapi.exception.user.UserExceptionStatus;
import com.idiots.openapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return new CustomUserDetails(userRepository.findByEmail(username)
                .orElseThrow(() -> new Exception404(UserExceptionStatus.USER_NOT_FOUND)));
    }
}
