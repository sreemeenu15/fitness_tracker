package com.sree.fitness_tracker.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "User registration request")
public class RegisterRequest {
    @Schema(description = "Full name", example = "Sree ")
    private String name;
    @Schema(description = "email", example = "sree@gmail.com")
    private String email;
    @Schema(description = "password", example = "sreeBackend123")
    private String password;
    @Schema(description = "age", example = "22")
    private Integer age;
    @Schema(description = "height", example = "151")
    private Double height;
    @Schema(description = "weight", example = "52")
    private Double weight;

}
