package com.idiots.openapi.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class KakaoTalkAlarmRequestDto {
    private String phoneNumber;     // 핸드폰 번호
    private String name;            // 성명
    private String date;            // 오늘 날짜
    private String city;            // 시
    private String district;        // 구

    @Builder
    public KakaoTalkAlarmRequestDto(String phoneNumber, String name, String date, String city, String district) {
        this.phoneNumber = phoneNumber;
        this.name = name;
        this.date = date;
        this.city = city;
        this.district = district;
    }
}
