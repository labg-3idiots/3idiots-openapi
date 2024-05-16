package com.idiots.openapi.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "region")
public class Region {
    @Id
    @Schema(description = "행정구역코드", example = "1135070000")
    private String regionCode;

    @Schema(description = "1단계", example = "서울특별시")
    private String firstRegion;

    @Schema(description = "2단계", example = "노원구")
    private String secondRegion;

    @Schema(description = "3단계", example = "상계8동")
    private String thirdRegion;

    @Schema(description = "격자 X", example = "61")
    private int x;

    @Schema(description = "격자 Y", example = "129")
    private int y;

    @Schema(description = "경도(초/100)", example = "127.05366666")
    private float longitude;

    @Schema(description = "위도(초/100", example = "37.663944444")
    private float latitude;
}
