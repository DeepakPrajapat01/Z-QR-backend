package com.zqr_backend.service;

import com.zqr_backend.dto.AttendanceRequest;
import com.zqr_backend.model.Attendance;
import com.zqr_backend.repository.AttendanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AttendanceService {

    private final AttendanceRepository attendanceRepository;

    public Attendance markAttendance(AttendanceRequest request) {

        Attendance attendance = Attendance.builder()
                .studentId(request.getStudentId())
                .subject(request.getSubject())
                .timestamp(LocalDateTime.now())
                .build();

        return attendanceRepository.save(attendance);
    }

    public List<Attendance> getStudentAttendance(String studentId) {
        return attendanceRepository.findByStudentId(studentId);
    }
}
