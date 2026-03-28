package com.sree.fitness_tracker.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
@Schema(description = "Data for bodyMetric")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LogBodyMetricRequest {

    @Schema(description = "Weight in kg" , example = "60")
    private BigDecimal weight;

    @Schema(description = "Height in cm" , example = "161")
    private BigDecimal height;

    @Schema(description = "Bodyfat in percentage" , example = "25")
    private BigDecimal bodyFatPercentage;

    @Schema(description = "Date you log data" , example = "2026-04-23")
    private LocalDate loggedDate;

}
