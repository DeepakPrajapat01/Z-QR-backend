package com.zqr_backend.security;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class QrTokenUtil {

    public String generateToken() {
        return UUID.randomUUID().toString();
    }
}
