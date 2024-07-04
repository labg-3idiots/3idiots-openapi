package com.idiots.openapi.dto;

import com.idiots.openapi.entity.Region;
import lombok.Builder;

@Builder
public record RegionResponseDto(
        String regionCode,
        String firstRegion,
        String secondRegion,
        int x,
        int y
) {
    public static RegionResponseDto of(Region region) {
        return RegionResponseDto.builder()
                .regionCode(region.getRegionCode())
                .firstRegion(region.getFirstRegion())
                .secondRegion(region.getSecondRegion())
                .x(region.getX())
                .y(region.getY())
                .build();
    }
}
