package com.idiots.openapi.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

// JSON 형태의 결과 구조대로 DTO생성
@Getter
@NoArgsConstructor
public class WeatherResponseJsonDto {
    private Response response;

    @Getter
    @NoArgsConstructor
    public static class Response {
        private Body body;

        @Getter
        @NoArgsConstructor
        public static class Body {
            private Items items;

            @Getter
            @NoArgsConstructor
            public static class Items {
                private List<WeatherResponseDto> item;
            }
        }
    }
}
