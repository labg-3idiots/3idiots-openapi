package com.idiots.openapi.controller;

import com.idiots.openapi.dto.ApiParamDto;
import com.idiots.openapi.dto.UserInterestRegionDto;
import com.idiots.openapi.entity.UserInterestRegion;
import com.idiots.openapi.service.OpenapiService;
import com.idiots.openapi.service.UserInterestRegionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.io.UnsupportedEncodingException;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class OpenapiController {

    private final OpenapiService openapiService;
    private final UserInterestRegionService userInterestRegionService;

    // 컨트롤러는 삭제예정
    @GetMapping("/open-api")
    public Mono<String> fetch(@RequestBody ApiParamDto apiParamDto) throws UnsupportedEncodingException {
        return openapiService.getOpenapi(apiParamDto);
    }

}
