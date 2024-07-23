package com.idiots.openapi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class VerificationData {
    private String code;
    private long timestamp;
}
