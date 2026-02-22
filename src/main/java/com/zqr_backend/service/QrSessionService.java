package com.zqr_backend.service;

import com.zqr_backend.dto.AttendanceRequest;
import com.zqr_backend.model.QrSession;
import com.zqr_backend.repository.QrSessionRepository;
import com.zqr_backend.security.QrTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class QrSessionService {

    private final QrSessionRepository qrSessionRepository;
    private final QrTokenUtil qrTokenUtil;

    public QrSession startSession(String subject, String teacherId) {

        qrSessionRepository.findByActiveTrue()
                .ifPresent(session -> {
                    session.setActive(false);
                    qrSessionRepository.save(session);
                });

        QrSession newSession = QrSession.builder()
                .subject(subject)
                .teacherId(teacherId)
                .currentToken(qrTokenUtil.generateToken())
                .active(true)
                .createdAt(LocalDateTime.now())
                .expiresAt(LocalDateTime.now().plusMinutes(5))
                .build();



        return qrSessionRepository.save(newSession);
    }

    public boolean validateToken(String token) {

        return qrSessionRepository.findByActiveTrue()
                .filter(session -> session.getExpiresAt().isAfter(LocalDateTime.now()))
                .map(session -> session.getCurrentToken().equals(token))
                .orElse(false);
    }



//    public boolean validateAndMarkAttendance(
//            String token,
//            String studentId,
//            String subject,
//            AttendanceService attendanceService
//    ) {
//
//        boolean valid = validateToken(token);
//
//        if (!valid) return false;
//
//        AttendanceRequest request = new AttendanceRequest();
//        request.setStudentId(studentId);
//        request.setSubject(subject);
//
//        attendanceService.markAttendance(request);
//
//        return true;
//    }



    public boolean validateAndMarkAttendance(
            String token,
            String studentId,
            String subject,
            AttendanceService attendanceService
    ) {

        try {

            boolean valid = validateToken(token);

            if (!valid) {
                System.out.println("TOKEN INVALID");
                return false;
            }

            AttendanceRequest request = new AttendanceRequest();
            request.setStudentId(studentId);
            request.setSubject(subject);

            attendanceService.markAttendance(request);

            return true;

        } catch (Exception e) {

            e.printStackTrace();   // ⭐ THIS WILL SHOW REAL ERROR
            return false;
        }
    }



}


