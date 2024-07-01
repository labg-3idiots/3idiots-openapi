package com.idiots.openapi.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "region_weather")
@ToString
public class RegionWeather {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "REGION_WEATHER_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(length = 8)
    private String date;

    @Column(length = 4)
    private String time;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_code")
    private Region region;

    @Schema(description = "카테고리", example = "PTY")
    @Column(length = 10)
    private String category;

    @Schema(description = "상태", example = "3")
    @Column(length = 10)
    private String condition;

    @Builder
    public RegionWeather(User user, String date, String time, Region region, String category, String condition) {
        this.user = user;
        this.date = date;
        this.time = time;
        this.region = region;
        this.category = category;
        this.condition = condition;
    }
}
