package com.idiots.openapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class OpenapiService {
    @Value("${openapi.host}")
    private String host;

    @Value("${openapi.endPoint}")
    private String endPoint;

    @Value("${openapi.serviceKey}")
    private String SERVICE_KEY;

    private final WebClient webClient;

    public Mono<String> getOpenapi() {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .scheme("http")
                        .host(host)
                        .path(endPoint)
                        .queryParam("serviceKey", SERVICE_KEY)
                        .queryParam("dataType", "JSON")
                        .queryParam("base_date", "20240523")
                        .queryParam("base_time", "0630")
                        .queryParam("nx", 120)
                        .queryParam("ny", 60)
                        .build())
                .retrieve()
                .bodyToMono(String.class);
    }
}