package com.idiots.openapi.controller;

import com.idiots.openapi.service.RegionService;
import com.idiots.openapi.utils.ApiUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/region")
@RestController
public class RegionController {
    private final RegionService regionService;

    @GetMapping("/")
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(ApiUtils.success(regionService.findAll()));
    }
}
