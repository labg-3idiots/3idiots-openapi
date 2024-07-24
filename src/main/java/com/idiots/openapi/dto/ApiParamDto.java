package com.idiots.openapi.dto;

import lombok.Builder;

@Builder
public record ApiParamDto(
        Integer numOfRows,
        String baseDate,
        String baseTime,
        Integer nx,
        Integer ny
) {
}
