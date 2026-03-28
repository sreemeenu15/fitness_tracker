package com.sree.fitness_tracker.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Schema(description = "Updating user goal")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateGoalRequest {

    @Schema(description = "Enter your target value" , example = "23")
    private BigDecimal targetValue;

    @Schema(description = "Enter date" , example = "2026-02-15")
    private LocalDate endDate;

}
