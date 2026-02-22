package com.zqr_backend.repository;

import com.zqr_backend.model.QrSession;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface QrSessionRepository extends MongoRepository<QrSession, String> {

    Optional<QrSession> findByActiveTrue();
}
