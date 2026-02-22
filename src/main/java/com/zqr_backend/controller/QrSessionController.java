package com.zqr_backend.controller;

import com.zqr_backend.dto.ScanAttendanceRequest;
import com.zqr_backend.model.QrSession;
import com.zqr_backend.repository.QrSessionRepository;
import com.zqr_backend.service.AttendanceService;
import com.zqr_backend.service.QrSessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/qr")
@RequiredArgsConstructor
public class QrSessionController {

    private final QrSessionService qrSessionService;
    private final AttendanceService attendanceService;
    private final QrSessionRepository qrSessionRepository;



    @PostMapping("/start")
    public QrSession startSession(
            @RequestParam String subject,
            @RequestParam String teacherId
    ) {
        return qrSessionService.startSession(subject, teacherId);
    }

    @GetMapping("/validate")
    public boolean validateToken(@RequestParam String token) {
        return qrSessionService.validateToken(token);
    }

    @PostMapping("/scan")
    public boolean scanAttendance(@RequestBody ScanAttendanceRequest request) {

        try {

            return qrSessionService.validateAndMarkAttendance(
                    request.getToken(),
                    request.getStudentId(),
                    request.getSubject(),
                    attendanceService
            );

        } catch (Exception e) {

            e.printStackTrace();   // ⭐ THIS WILL SHOW REAL ERROR
            return false;
        }
    }


    @GetMapping("/active")
    public ResponseEntity<QrSession> getActiveSession() {

        Optional<QrSession> session = qrSessionRepository.findByActiveTrue();

        if (session.isPresent()) {
            return ResponseEntity.ok(session.get());
        } else {
            return ResponseEntity.noContent().build();
        }
    }







}

