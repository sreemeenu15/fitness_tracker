package com.sree.fitness_tracker.controller;

import com.sree.fitness_tracker.dto.request.LoginRequest;
import com.sree.fitness_tracker.dto.request.RegisterRequest;
import com.sree.fitness_tracker.dto.response.AuthResponse;
import com.sree.fitness_tracker.service.AuthService;
import com.sree.fitness_tracker.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;
@Tag(name = "Authentication", description = "User login and registration")
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @Operation(
            summary = "Register new user",
            description = "Creates a new user account"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User registered"),
            @ApiResponse(responseCode = "400", description = "Invalid request")
    })
    @PostMapping("/register")
    public AuthResponse register(@RequestBody RegisterRequest request) {
        return authService.register(request);
    }

    @Operation(
            summary = "User login",
            description = "Authenticates user and returns JWT token"
    )
    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }
}
