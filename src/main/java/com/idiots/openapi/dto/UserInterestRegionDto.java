package com.idiots.openapi.dto;

import com.idiots.openapi.entity.UserInterestRegion;
import lombok.Builder;

@Builder
public record UserInterestRegionDto(
    long userId,
    String regionCode,
    int nx,
    int ny
) {

    public static UserInterestRegionDto of(UserInterestRegion userInterestRegion) {
        return UserInterestRegionDto.builder()
                .userId(userInterestRegion.getUser().getId())
                .regionCode(userInterestRegion.getRegion().getRegionCode())
                .nx(userInterestRegion.getRegion().getX())
                .ny(userInterestRegion.getRegion().getY())
                .build();
    }
}

