package com.zqr_backend.scheduler;

import com.zqr_backend.model.QrSession;
import com.zqr_backend.repository.QrSessionRepository;
import com.zqr_backend.security.QrTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class QrTokenScheduler {

    private final QrSessionRepository qrSessionRepository;
    private final QrTokenUtil qrTokenUtil;

    @Scheduled(fixedRate = 2000)
    public void rotateToken() {

        qrSessionRepository.findByActiveTrue().ifPresent(session -> {

            if (session.getExpiresAt() == null ||
                    session.getExpiresAt().isBefore(LocalDateTime.now())) {

                session.setActive(false);
                qrSessionRepository.save(session);
                return;
            }
            session.setCurrentToken(qrTokenUtil.generateToken());
            qrSessionRepository.save(session);
        });
    }
}
