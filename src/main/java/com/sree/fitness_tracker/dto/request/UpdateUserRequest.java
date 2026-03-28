package com.sree.fitness_tracker.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "Updating User profile" )
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserRequest {

    @Schema(description = "Your name" , example = "Aasmann Sree")
    private String name;

    @Schema(description = "Your age" , example = "16")
    private Integer age;

    @Schema(description = "Height in cm" , example = "154")
    private Double height;

    @Schema(description = "Weight in kg" , example = "78")
    private Double weight;

}