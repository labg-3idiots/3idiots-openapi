package com.idiots.openapi.service;

import com.idiots.openapi.dto.KakaoTalkAlarmRequestDto;
import com.idiots.openapi.dto.UserInterestRegionDto;
import com.idiots.openapi.repository.UserInterestRegionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class UserInterestRegionService {
    private final UserInterestRegionRepository userInterestRegionRepository;

    // region_code가 같은 것은 조회X(openapi 호출 시, 같은 지역은 조회안하기 위함)
    @Transactional(readOnly = true)
    public List<UserInterestRegionDto> selectUserInterestRegionList() {
        return userInterestRegionRepository.findAllDistinctRegionCode()
                .stream()
                .map(UserInterestRegionDto::of)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<KakaoTalkAlarmRequestDto> selectUserInterestRegionForRegionCodeList(String regionCode) {
        return userInterestRegionRepository.findAllByRegionRegionCode(regionCode)
                .stream()
                .map(KakaoTalkAlarmRequestDto::of)
                .toList();
    }
}
