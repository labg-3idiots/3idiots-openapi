package com.idiots.openapi.dto;

import com.idiots.openapi.entity.User;
import com.idiots.openapi.entity.UserRole;
import com.idiots.openapi.entity.UserStatus;
import lombok.Builder;

@Builder
public record UserRequestDto(
        String email,
        String password,
        String phoneNumber
) {

    public User toEntity() {
        return User.builder()
                .email(email)
                .password(password)
                .phoneNumber(phoneNumber)
                .role(UserRole.USER)
                .build();
    }
}
