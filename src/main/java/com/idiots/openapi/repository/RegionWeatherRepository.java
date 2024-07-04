package com.idiots.openapi.repository;

import com.idiots.openapi.entity.RegionWeather;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RegionWeatherRepository extends JpaRepository<RegionWeather, Long> {
    List<RegionWeather> findAllByFcstDateAndFcstValueNot(String now, String fcstValue);
}
