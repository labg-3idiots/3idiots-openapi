package com.idiots.openapi.service;

import com.idiots.openapi.dto.ApiParamDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
@Slf4j
public class OpenapiService {
    @Value("${openapi.host}")
    private String host;

    @Value("${openapi.endPoint}")
    private String endPoint;

    @Value("${openapi.serviceKey}")
    private String SERVICE_KEY;

    private final WebClient webClient;

    // 단기예보조회
    public Mono<String> getOpenapi(ApiParamDto apiParamDto) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .scheme("http")
                        .host(host)
                        .path(endPoint)
                        .queryParam("serviceKey", SERVICE_KEY)
                        .queryParam("numOfRows", apiParamDto.numOfRows()) // numOfRows = 10 -> 1시간
                        .queryParam("dataType", "JSON")
                        .queryParam("base_date", apiParamDto.baseDate()) //20240627
                        .queryParam("base_time", apiParamDto.baseTime()) // 05시 기준
                        .queryParam("nx", apiParamDto.nx()) //61
                        .queryParam("ny", apiParamDto.ny()) //126 (강남구)
                        .build())
                .retrieve()
                .bodyToMono(String.class);
    }

}