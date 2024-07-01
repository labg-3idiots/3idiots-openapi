package com.idiots.openapi.dto;

import com.idiots.openapi.entity.Region;
import com.idiots.openapi.entity.RegionWeather;
import com.idiots.openapi.entity.User;
import lombok.Builder;

@Builder
public record WeatherResponseDto(
        String fcstDate,
        String fcstTime,
        String category,
        String fcstValue
) {
    public RegionWeather toEntity() {
        return RegionWeather.builder()
                .date(fcstDate())
                .time(fcstTime())
                .category(category())
                .condition(fcstValue())
                .build();
    }
}