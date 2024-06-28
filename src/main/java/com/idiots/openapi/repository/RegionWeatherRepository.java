package com.idiots.openapi.repository;

import com.idiots.openapi.entity.RegionWeather;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegionWeatherRepository extends JpaRepository<RegionWeather, Long> {
}
