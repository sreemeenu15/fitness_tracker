package com.sree.fitness_tracker.dto.request;

import com.sree.fitness_tracker.entity.GoalType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Schema(description = "Fields for creating goal")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateGoalRequest {

    @Schema(description = "goalType", example = "Sleep_Hours")
    private GoalType goalType;

    @Schema(description = "TargetValue for goal", example = "7")
    private BigDecimal targetValue;

    @Schema(description = "startDate for goal", example = "2026-04-14")
    private LocalDate startDate;

    @Schema(description = "endDate for goal", example = "2026-04-15")
    private LocalDate endDate;

}