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

    @Schema(description = "Date you log activity", example = "2026-04-14")
    private BigDecimal sleepHours;

    @Schema(description = "Date you log activity", example = "2026-04-14")
    private Integer walkingSteps;

    @Schema(description = "Date you log activity", example = "2026-04-14")
    private BigDecimal hydrationLitres;

    @Schema(description = "Date you log activity", example = "2026-04-14")
    private Integer exerciseMinutes;

    @Schema(description = "Date you log activity", example = "2026-04-14")
    private ActivityStatus activityStatus;
}
