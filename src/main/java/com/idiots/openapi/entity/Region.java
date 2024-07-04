package com.idiots.openapi.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "region")
public class Region {
    @Id
    @Schema(description = "행정구역코드", example = "1135070000")
    @Column(name = "region_code")
    private String regionCode;

    @Schema(description = "1단계", example = "서울특별시")
    private String firstRegion;

    @Schema(description = "2단계", example = "노원구")
    private String secondRegion;

    @Schema(description = "격자 X", example = "61")
    private int x;

    @Schema(description = "격자 Y", example = "126")
    private int y;

    @Builder
    public Region(String regionCode, String firstRegion, String secondRegion, int x, int y) {
        this.regionCode = regionCode;
        this.firstRegion = firstRegion;
        this.secondRegion = secondRegion;
        this.x = x;
        this.y = y;
    }
}
