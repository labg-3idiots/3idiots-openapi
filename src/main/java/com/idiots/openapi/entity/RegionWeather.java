package com.idiots.openapi.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "region_weather")
public class RegionWeather {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "REGION_WEATHER_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    private Region region;

    @Column
    private String date;

    @Column
    private String time;

    @Schema(description = "하늘 상태", example = "3")
    @Column
    private Integer sky_condition;

    @Schema(description = "강수 형태", example = "1")
    @Column
    private Integer pty_condition;
}
