package com.idiots.openapi.scheduler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.idiots.openapi.dto.ApiParamDto;
import com.idiots.openapi.dto.UserInterestRegionDto;
import com.idiots.openapi.dto.WeatherResponseDto;
import com.idiots.openapi.dto.WeatherResponseJsonDto;
import com.idiots.openapi.entity.RegionWeather;
import com.idiots.openapi.repository.RegionRepository;
import com.idiots.openapi.repository.RegionWeatherRepository;
import com.idiots.openapi.repository.UserRepository;
import com.idiots.openapi.service.OpenapiService;
import com.idiots.openapi.service.RegionWeatherService;
import com.idiots.openapi.service.UserInterestRegionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 매일 오전 5시에 알림 발송하는 스케줄러
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class AlarmScheduler {
    private final OpenapiService openapiService;
    private final RegionWeatherService regionWeatherService;
    private final UserInterestRegionService userInterestRegionService;

    private static final DateTimeFormatter fomatter = DateTimeFormatter.ofPattern("yyyyMMdd");
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private final UserRepository userRepository;
    private final RegionRepository regionRepository;

    //@Scheduled(cron = "0 0 5 * * *") // 매일 오전 5시에 실행
    @Scheduled(fixedRate = 3000)
    public void run() {
        String now = fomatter.format(LocalDate.now());
        log.info("현재시간 : {}", now);

        // DB에 INSERT된 데이터(당일)중 강수형태가 0이 아닌 데이터들만 가져옴
        List<WeatherResponseDto> weatherResponseDtoList = regionWeatherService.selectRegionWeatherList(now);

        // regionCode 중복제거를 위한 SET
        Set<String> rainyRegionCodeList = weatherResponseDtoList
                .stream()
                .map(WeatherResponseDto::regionCode)
                .collect(Collectors.toSet());
        log.info("비오는 지역코드 : {}", rainyRegionCodeList);

        // 비오는 지역코드로 user_interest_region에서 조회하여 조회된 user에게 알림 발송(카카오톡)

    }
}
