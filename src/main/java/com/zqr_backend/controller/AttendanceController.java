package com.zqr_backend.controller;

import com.zqr_backend.dto.AttendanceRequest;
import com.zqr_backend.model.Attendance;
import com.zqr_backend.service.AttendanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/attendance")
@RequiredArgsConstructor
public class AttendanceController {

    private final AttendanceService attendanceService;

    @PostMapping("/mark")
    public Attendance markAttendance(@RequestBody AttendanceRequest request) {
        return attendanceService.markAttendance(request);
    }

    @GetMapping("/student/{studentId}")
    public List<Attendance> getStudentAttendance(@PathVariable String studentId) {
        return attendanceService.getStudentAttendance(studentId);
    }
}
