package com.sree.fitness_tracker.dto.response;

import com.sree.fitness_tracker.entity.GoalType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "User fitness goal information")
public class GoalResponse {

    @Schema(description = "Goal identifier", example = "1")
    private Long id;

    @Schema(description = "Type of fitness goal", example = "EXERCISE_MINUTES")
    private GoalType goalType;

    @Schema(description = "Target value to achieve", example = "60")
    private BigDecimal targetValue;

    @Schema(description = "Goal start date", example = "2026-03-20")
    private LocalDate startDate;

    @Schema(description = "Goal end date", example = "2026-04-20")
    private LocalDate endDate;

}
