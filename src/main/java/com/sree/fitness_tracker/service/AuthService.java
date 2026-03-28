package com.sree.fitness_tracker.service;

import com.sree.fitness_tracker.dto.request.LoginRequest;
import com.sree.fitness_tracker.dto.request.RegisterRequest;
import com.sree.fitness_tracker.dto.response.AuthResponse;

public interface AuthService {

    AuthResponse register(RegisterRequest request);

    AuthResponse login(LoginRequest request);

}