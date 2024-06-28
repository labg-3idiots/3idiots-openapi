package com.idiots.openapi.service;

import com.idiots.openapi.entity.RegionWeather;
import com.idiots.openapi.repository.RegionWeatherRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class RegionWeatherService {

    private final RegionWeatherRepository regionWeatherRepository;

    // 단기예보 조회한 데이터 정제 후 DB INSERT(RegionWeather)
    public RegionWeather insertRegionWeather(RegionWeather regionWeather) {
        return regionWeatherRepository.save(regionWeather);
    }
}
