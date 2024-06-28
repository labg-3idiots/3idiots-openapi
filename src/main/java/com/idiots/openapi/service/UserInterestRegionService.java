package com.idiots.openapi.service;

import com.idiots.openapi.dto.UserInterestRegionDto;
import com.idiots.openapi.repository.UserInterestRegionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class UserInterestRegionService {
    private final UserInterestRegionRepository userInterestRegionRepository;

    @Transactional(readOnly = true)
    public List<UserInterestRegionDto> findAll() {
        return userInterestRegionRepository.findAll().stream()
                .map(UserInterestRegionDto::of)
                .toList();
    }
}
