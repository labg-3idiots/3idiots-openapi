package com.idiots.openapi.service;

import com.idiots.openapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Slf4j
@RequiredArgsConstructor
@Service
public class BizmService {
    private final UserRepository userRepository;

    @Value("${bizm.host}")
    private String host;

    @Value("${bizm.endPoint}")
    private String endPoint;

    @Value("${bizm.profileKey}")
    private String profileKey;


    private final WebClient webClient;

    public void validatePhoneNumber(String phoneNumber) {

    }
}
