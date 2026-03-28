package com.sree.fitness_tracker.dto.response;

import com.sree.fitness_tracker.entity.ActivityStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "User activity log response")
public class ActivityResponse {

    @Schema(description = "Date when the activity was recorded", example = "2026-03-22")
    private LocalDate date;

    @Schema(description = "Number of sleeping hours", example = "7")
    private BigDecimal sleepHours;

    @Schema(description = "Num of steps walked daily", example = "8000")
    private Integer walkingSteps;

    @Schema(description = "Hydration in litres", example = "3")
    private BigDecimal hydrationLitres;

    @Schema(description = "Num of exercise in minutes", example = "45")
    private Integer exerciseMinutes;

    @Schema(description = "Status of the activity for the day", example = "COMPLETED")
    private ActivityStatus activityStatus;
}
