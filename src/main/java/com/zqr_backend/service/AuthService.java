package com.zqr_backend.service;

import com.zqr_backend.dto.LoginRequest;
import com.zqr_backend.dto.LoginResponse;
import com.zqr_backend.dto.SignupRequest;
import com.zqr_backend.model.User;
import com.zqr_backend.repository.UserRepository;
import com.zqr_backend.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;


    public User signup(SignupRequest request) {

        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(request.getPassword())
                .role(request.getRole())
                .build();

        return userRepository.save(user);
    }

    public LoginResponse login(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!user.getPassword().equals(request.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        String token = jwtUtil.generateToken(user.getEmail());

        return new LoginResponse(
                token,
                user.getRole(),
                user.getId()
        );
    }
}