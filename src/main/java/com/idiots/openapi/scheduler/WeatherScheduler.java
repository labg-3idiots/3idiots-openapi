package com.idiots.openapi.scheduler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.idiots.openapi.dto.ApiParamDto;
import com.idiots.openapi.dto.UserInterestRegionDto;
import com.idiots.openapi.service.OpenapiService;
import com.idiots.openapi.service.RegionWeatherService;
import com.idiots.openapi.service.UserInterestRegionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

// 단기예보조회 API를 통해 데이터를 읽어오고, 읽어온 데이터를 정제해서 DB INSERT하는 스케줄러
@Component
@RequiredArgsConstructor
@Slf4j
public class WeatherScheduler {
    private final OpenapiService openapiService;
    private final RegionWeatherService regionWeatherService;
    private final UserInterestRegionService userInterestRegionService;
    private static final DateTimeFormatter fomatter = DateTimeFormatter.ofPattern("yyyyMMdd");
    private static final ObjectMapper objectMapper = new ObjectMapper();

//    @Scheduled(cron = "0 0/5 * * * ?")
    @Scheduled(fixedRate = 3000)
    public void run() {
        String now = fomatter.format(LocalDate.now());
        log.info("현재시간 : {}", now);

        List<UserInterestRegionDto> userInterestRegionList = userInterestRegionService.findAll();
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

            // 조회 데이터로 정제하기(SKY(하늘상태) : 4(흐림), PTY(강수형태) : 0(없음),1(비),2(비/눈),3(눈),4(소나기))
        }

    }
}
