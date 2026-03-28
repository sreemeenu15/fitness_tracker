package com.sree.fitness_tracker.controller;

import com.sree.fitness_tracker.dto.request.ChangePasswordRequest;
import com.sree.fitness_tracker.dto.request.UpdateUserRequest;
import com.sree.fitness_tracker.dto.response.MessageResponse;
import com.sree.fitness_tracker.dto.response.UserResponse;
import com.sree.fitness_tracker.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

import lombok.RequiredArgsConstructor;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.web.bind.annotation.*;

@Tag(name = "User Management", description = "Operations related to users")
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // Get current user
    @Operation(
            summary = "Get current user",
            description = "Fetch authenticated user's profile"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User found"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/me")
    public UserResponse getCurrentUser(
            @AuthenticationPrincipal UserDetails userDetails) {

        String email = userDetails.getUsername();
        return userService.getUserByEmail(email);
    }


    // Update profile
    @Operation(
            summary = "Update user profile",
            description = "Update authenticated user's profile"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User updated"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @SecurityRequirement(name = "bearerAuth")
    @PutMapping("/me")
    public UserResponse updateUser(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody UpdateUserRequest request) {

        String email = userDetails.getUsername();
        return userService.updateUserProfile(email, request);
    }


    // Change password
    @Operation(
            summary = "Change password",
            description = "Change authenticated user's password"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Password updated"),
            @ApiResponse(responseCode = "400", description = "Invalid password")
    })
    @SecurityRequirement(name = "bearerAuth")
    @PutMapping("/me/password")
    public MessageResponse changePassword(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody ChangePasswordRequest request) {

        String email = userDetails.getUsername();
        return userService.changePassword(email, request);
    }


    // Delete account
    @Operation(
            summary = "Delete account",
            description = "Delete authenticated user's account"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User deleted"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @SecurityRequirement(name = "bearerAuth")
    @DeleteMapping("/me")
    public MessageResponse deleteUser(
            @AuthenticationPrincipal UserDetails userDetails) {

        String email = userDetails.getUsername();
        return userService.deleteUser(email);
    }

}