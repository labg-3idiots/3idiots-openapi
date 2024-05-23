package com.idiots.openapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@RequiredArgsConstructor
@Service
public class BizMService {
    @Value("${bizm.host}")
    private String host;

    @Value("${bizm.endPoint}")
    private String endPoint;

    @Value("${bizm.profileKey}")
    private String profileKey;


    private final WebClient webClient;


}
