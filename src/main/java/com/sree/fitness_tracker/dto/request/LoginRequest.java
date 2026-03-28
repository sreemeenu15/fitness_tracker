package com.sree.fitness_tracker.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Schema(description = "Login request fields")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {

    @Schema(description = "email", example = "sree@gmail.com")
    private String email;

    @Schema(description = "password", example = "sree@2215")
    private String password;

}