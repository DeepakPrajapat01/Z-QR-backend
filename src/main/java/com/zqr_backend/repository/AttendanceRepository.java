package com.zqr_backend.repository;

import com.zqr_backend.model.Attendance;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface AttendanceRepository extends MongoRepository<Attendance, String> {


    long countBySessionId(String sessionId);


    List<Attendance> findByStudentId(String studentId);
    boolean existsByStudentIdAndSessionId(String studentId, String sessionId);

}

