package com.idiots.openapi.dto;

import com.idiots.openapi.entity.Region;
import lombok.Builder;

@Builder
public record RegionResponseDto(
        String regionCode,
        String firstRegion,
        String secondRegion,
        String thirdRegion,
        int x,
        int y,
        float longitude,
        float latitude
) {
    public static RegionResponseDto of(Region region) {
        return RegionResponseDto.builder()
                .regionCode(region.getRegionCode())
                .firstRegion(region.getFirstRegion())
                .secondRegion(region.getSecondRegion())
                .thirdRegion(region.getThirdRegion())
                .x(region.getX())
                .y(region.getY())
                .longitude(region.getLongitude())
                .latitude(region.getLatitude())
                .build();
    }
}
