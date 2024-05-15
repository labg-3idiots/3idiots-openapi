package com.idiots.openapi.controller;

import com.idiots.openapi.service.OpenapiService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.io.UnsupportedEncodingException;

@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class OpenapiController {

    private final OpenapiService openapiService;

    @GetMapping("open-api")
    public Mono<String> fetch() throws UnsupportedEncodingException {
        return openapiService.getOpenapi();
    }

}
