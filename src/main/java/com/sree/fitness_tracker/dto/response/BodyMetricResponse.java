package com.sree.fitness_tracker.dto.response;



import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Body metrics recorded by the user")
public class BodyMetricResponse {


    private Long id;

    @Schema(description = "Weight of the user in kilograms", example = "72.5")
    private BigDecimal weight;

    @Schema(description = "Height of the user in centimeters", example = "175")
    private BigDecimal height;

    @Schema(description = "Body fat percentage", example = "18")
    private BigDecimal bodyFatPercentage;

    @Schema(description = "Date when body metrics were logged", example = "2026-03-22")
    private LocalDate loggedDate;

}