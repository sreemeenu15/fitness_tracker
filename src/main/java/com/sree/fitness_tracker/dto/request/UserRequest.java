package com.sree.fitness_tracker.dto.request;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Schema(description = "Request regarding user details")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {
    @Schema(description = "Your name" , example = "Aasmann Sree")
    private String name;

    @Schema(description = "Your email" , example = "abc12@gmail.com")
    private String email;

    @Schema(description = "Your age" , example = "22")
    private Integer age;

    @Schema(description = "Height in cm" , example = "151")
    private Double height;

    @Schema(description = "weight in kg" , example = "52")
    private Double weight;

}
