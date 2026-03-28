package com.sree.fitness_tracker.dto.response;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "User profile information")
public class UserResponse {

    @Schema(description = "Unique user identifier", example = "1")
    private Long id;

    @Schema(description = "Full name of the user", example = "Sree Alfred")
    private String name;

    @Schema(description = "User email address", example = "sree@gmail.com")
    private String email;

    @Schema(description = "Age of the user", example = "23")
    private int age;

    @Schema(description = "Height of the user in centimeters", example = "175")
    private double height;

    @Schema(description = "Weight of the user in kilograms", example = "72")
    private double weight;

}
