package com.zqr_backend.dto;

import lombok.Data;

@Data
public class ScanAttendanceRequest {

    private String token;
    private String studentId;
    private String subject;
}
