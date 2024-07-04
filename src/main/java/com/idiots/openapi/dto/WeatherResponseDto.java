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
        String fcstValue,
        String category,
        String regionCode
) {

    public RegionWeather toEntity(String regionCode) {
        return RegionWeather.builder()
                .fcstDate(fcstDate())
                .fcstTime(fcstTime())
                .fcstValue(fcstValue())
                .category(category())
                .region(Region.builder()
                        .regionCode(regionCode)
                        .build())
                .build();
    }

    public static WeatherResponseDto of(RegionWeather regionWeather) {
        return WeatherResponseDto.builder()
                .fcstDate(regionWeather.getFcstDate())
                .fcstTime(regionWeather.getFcstTime())
                .fcstValue(regionWeather.getFcstValue())
                .regionCode(regionWeather.getRegion().getRegionCode())
                .category(regionWeather.getCategory())
                .build();
    }
}