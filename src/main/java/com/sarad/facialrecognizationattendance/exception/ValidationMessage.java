package com.sarad.facialrecognizationattendance.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ValidationMessage {
    private int statusCode;
    private Date timestamp;
    private String description;
    private Map<String, String> message;
}
