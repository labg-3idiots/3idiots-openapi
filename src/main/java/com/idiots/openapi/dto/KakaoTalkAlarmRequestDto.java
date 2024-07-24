package com.idiots.openapi.dto;

import com.idiots.openapi.entity.User;
import com.idiots.openapi.entity.UserInterestRegion;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@ToString
public class KakaoTalkAlarmRequestDto {
    private String phoneNumber;     // 핸드폰 번호
    private String name;            // 성명
    private LocalDate date;            // 오늘 날짜
    private String firstRegion;     // 시
    private String secondRegion;    // 구

    @Builder
    public KakaoTalkAlarmRequestDto(String phoneNumber, String name, LocalDate date, String firstRegion, String secondRegion) {
        this.phoneNumber = phoneNumber;
        this.name = name;
        this.date = date;
        this.firstRegion = firstRegion;
        this.secondRegion = secondRegion;
    }

    public static KakaoTalkAlarmRequestDto of(UserInterestRegion userInterestRegion) {
        return KakaoTalkAlarmRequestDto.builder()
                .name(userInterestRegion.getUser().getName())
                .phoneNumber(userInterestRegion.getUser().getPhoneNumber())
                .date(LocalDate.now())
                .firstRegion(userInterestRegion.getRegion().getFirstRegion())
                .secondRegion(userInterestRegion.getRegion().getSecondRegion())
                .build();
    }
}
