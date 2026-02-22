package com.zqr_backend.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "qr_sessions")
public class QrSession {

    @Id
    private String id;

    private String subject;
    private String teacherId;

    private String currentToken;

    private boolean active;

    private LocalDateTime createdAt;

    private LocalDateTime expiresAt;

}
