package com.example.lovable_clone.service;

import com.example.lovable_clone.dto.auth.AuthResponse;
import com.example.lovable_clone.dto.auth.LoginRequest;
import com.example.lovable_clone.dto.auth.SignUpRequest;
import org.jspecify.annotations.Nullable;

public interface AuthService {
    AuthResponse signUp(SignUpRequest request);

    @Nullable AuthResponse login(LoginRequest request);
}
