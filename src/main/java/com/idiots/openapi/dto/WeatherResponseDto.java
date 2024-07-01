package com.idiots.openapi.dto;

import com.idiots.openapi.entity.Region;
import com.idiots.openapi.entity.RegionWeather;
import com.idiots.openapi.entity.User;
import lombok.Builder;
import lombok.Setter;

@Builder
public record WeatherResponseDto(
        String fcstDate,
        String fcstTime,
        String category,
        String fcstValue,
        Long userId,
        String regionCode
) {

    public RegionWeather toEntity(Long userId, String regionCode) {
        return RegionWeather.builder()
                .date(fcstDate())
                .time(fcstTime())
                .category(category())
                .condition(fcstValue())
                .region(Region.builder()
                        .regionCode(regionCode)
                        .build())
                .user(User.builder()
                        .id(userId)
                        .build())
                .build();
    }
}