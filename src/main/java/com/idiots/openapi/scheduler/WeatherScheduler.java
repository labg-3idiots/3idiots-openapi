package com.idiots.openapi.scheduler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.idiots.openapi.dto.ApiParamDto;
import com.idiots.openapi.dto.UserInterestRegionDto;
import com.idiots.openapi.dto.WeatherResponseDto;
import com.idiots.openapi.dto.WeatherResponseJsonDto;
import com.idiots.openapi.entity.Region;
import com.idiots.openapi.entity.RegionWeather;
import com.idiots.openapi.entity.User;
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

/**
 * 단기예보조회 API를 통해 데이터를 읽어오고, 읽어온 데이터를 정제해서 DB INSERT하는 스케줄러
 * 매일 오전 3시에 실행
*/

@Component
@RequiredArgsConstructor
@Slf4j
public class WeatherScheduler {
    private final OpenapiService openapiService;
    private final RegionWeatherService regionWeatherService;
    private final UserInterestRegionService userInterestRegionService;

    private static final DateTimeFormatter fomatter = DateTimeFormatter.ofPattern("yyyyMMdd");
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Scheduled(cron = "0 0 3 * * *") // 매일 오전 3시에 실행
//    @Scheduled(fixedRate = 30000)
    public void run() {
        String now = fomatter.format(LocalDate.now());
        log.info("현재날짜 : {}", now);

        List<UserInterestRegionDto> userInterestRegionList = userInterestRegionService.selectUserInterestRegionList();
        log.info("관심지역 테이블 : {}", userInterestRegionList);

        // 단기예보 조회를 위한 데이터 바인딩
        for(UserInterestRegionDto userInterestRegionDto : userInterestRegionList) {
            ApiParamDto apiParamDto= ApiParamDto.builder()
                    .numOfRows(250)
                    .baseDate(now)
                    .baseTime("0200")
                    .nx(userInterestRegionDto.nx())
                    .ny(userInterestRegionDto.ny())
                    .build();
            log.info("데이터 바인딩 결과 : {}", apiParamDto);

            String openapiResult = openapiService.getOpenapi(apiParamDto).block(); // 동기처리
            log.info("단기예보 조회 결과 : {}", openapiResult);

            /**
             * 조회 데이터로 정제하기
             * PTY(강수형태) : 0(없음),1(비),2(비/눈),3(눈),4(소나기)
             */
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            regionWeatherService.insertRegionWeatherList(resultAddList(openapiResult, userInterestRegionDto));
            log.info("DB INSERT LIST : {}", resultAddList(openapiResult, userInterestRegionDto));
        }
    }

    // 조회한 데이터 정제 후 RegionWeather List에 담는 메서드
    public List<RegionWeather> resultAddList(String openapiResult, UserInterestRegionDto userInterestRegionDto) {
        List<RegionWeather> resultList = new ArrayList<>();
        try {
            WeatherResponseJsonDto weatherResponseJsonDto = objectMapper.readValue(openapiResult, WeatherResponseJsonDto.class);
            log.info("DTO 결과 : {}", weatherResponseJsonDto.getResponse().getBody().getItems().getItem());
            List<WeatherResponseDto> weatherResponseDtoList = weatherResponseJsonDto.getResponse().getBody().getItems().getItem();

            for(WeatherResponseDto weatherResponseDto : weatherResponseDtoList) {
                if(weatherResponseDto.category().equals("PTY")) {
                    log.info("강수형태 dto : {}", weatherResponseDto);
                    resultList.add(weatherResponseDto.toEntity(userInterestRegionDto.regionCode()));
                }
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return resultList;
    }
}
