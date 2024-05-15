package com.idiots.openapi.controller;

import com.idiots.openapi.entity.Region;
import com.idiots.openapi.repository.RegionRepository;
import com.idiots.openapi.service.OpenapiService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class OpenapiController {

    private final OpenapiService openapiService;

    private final RegionRepository regionRepository;

    @GetMapping("open-api")
    public Object fetch() throws UnsupportedEncodingException {
        return openapiService.getOpenapi();
    }

    @GetMapping("region-test")
    public List<Region> getAllRegions() throws UnsupportedEncodingException {
        return regionRepository.getRegionTest();
    }

    @GetMapping("region-test2")
    public List<Region> getRegionsByFirstRegion(String firstRegion) throws UnsupportedEncodingException {
        return regionRepository.getRegionByFirstRegion(firstRegion);
    }
}
