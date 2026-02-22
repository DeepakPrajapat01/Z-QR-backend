package com.zqr_backend.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "attendance")
public class Attendance {

    @Id
    private String id;
    private String sessionId;


    private String studentId;
    private String subject;
    private LocalDateTime timestamp;
}
