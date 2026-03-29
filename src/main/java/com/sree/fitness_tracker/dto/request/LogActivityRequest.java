package com.sree.fitness_tracker.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;


import com.sree.fitness_tracker.entity.ActivityStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
@Schema(description = "fields for logging Activity." )
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LogActivityRequest {
    @Schema(description = "Date you log activity", example = "2026-04-14")
    private LocalDate date;

    @Schema(description = "SleepHours", example = "7")
    private BigDecimal sleepHours;

    @Schema(description = "Number of steps", example = "8000")
    private Integer walkingSteps;

    @Schema(description = "Hydration litres", example = "3.5")
    private BigDecimal hydrationLitres;

    @Schema(description = "Exercise in minutes", example = "45")
    private Integer exerciseMinutes;

    @Schema(description = "activityStatus", example = "PENDING")
    private ActivityStatus activityStatus;
}
