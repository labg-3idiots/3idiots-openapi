package com.idiots.openapi.service;

import com.idiots.openapi.dto.RegionResponseDto;
import com.idiots.openapi.entity.Region;
import com.idiots.openapi.repository.RegionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class RegionService {
    private final RegionRepository regionRepository;

    public List<RegionResponseDto> findAll() {
        return regionRepository.findAll()
                .stream()
                .map(RegionResponseDto::of)
                .toList();
    }
}
