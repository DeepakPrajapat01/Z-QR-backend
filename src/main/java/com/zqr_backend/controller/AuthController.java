package com.zqr_backend.controller;

import com.zqr_backend.dto.LoginRequest;
import com.zqr_backend.dto.LoginResponse;
import com.zqr_backend.dto.SignupRequest;
import com.zqr_backend.model.User;
import com.zqr_backend.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public User signup(@RequestBody SignupRequest request) {
        return authService.signup(request);
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }

}
