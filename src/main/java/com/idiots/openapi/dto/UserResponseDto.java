package com.idiots.openapi.dto;

import com.idiots.openapi.entity.User;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record UserResponseDto(
    String email,
    String name,
    String phoneNumber,
    String status,
    String role,
    LocalDateTime createAt,
    LocalDateTime lastLogin
) {

    public static UserResponseDto of(User user) {
        return UserResponseDto.builder()
                .email(user.getEmail())
                .name(user.getName())
                .phoneNumber(user.getPhoneNumber())
                .status(user.getStatus().toString())
                .role(user.getRole().toString())
                .createAt(user.getCreatedAt())
                .lastLogin(user.getLastLogin())
                .build();
    }
}

