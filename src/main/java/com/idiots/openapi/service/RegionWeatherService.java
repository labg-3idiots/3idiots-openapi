package com.idiots.openapi.service;

import com.idiots.openapi.dto.UserResponseDto;
import com.idiots.openapi.dto.WeatherResponseDto;
import com.idiots.openapi.entity.RegionWeather;
import com.idiots.openapi.repository.RegionWeatherRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class RegionWeatherService {

    private final RegionWeatherRepository regionWeatherRepository;

    // 단기예보 조회한 데이터 정제 후 DB INSERT(RegionWeather)
    public List<RegionWeather> insertRegionWeatherList(List<RegionWeather> regionWeatherList) {
        return regionWeatherRepository.saveAll(regionWeatherList);
    }

    public List<WeatherResponseDto> selectRegionWeatherList(String now) {
        List<WeatherResponseDto> resultList = regionWeatherRepository.findAllByFcstDateAndFcstValueNot(now, "0")
                .stream()
                .map(WeatherResponseDto::of)
                .toList();

        return resultList;
    }
}
