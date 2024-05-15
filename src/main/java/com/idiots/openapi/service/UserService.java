package com.idiots.openapi.service;

import com.idiots.openapi.dto.UserResponseDto;
import com.idiots.openapi.exception.Exception404;
import com.idiots.openapi.exception.user.UserExceptionStatus;
import com.idiots.openapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    public List<UserResponseDto> findAll() {
        return userRepository.findAll()
                .stream()
                .map(UserResponseDto::of)
                .toList();
    }

    public UserResponseDto findById(Long userId) {
        return UserResponseDto.of(
                userRepository
                        .findById(userId)
                        .orElseThrow(() -> new Exception404(UserExceptionStatus.USER_NOT_FOUND))
        );
    }

}
