package com.sree.fitness_tracker.dto.response;

import com.sree.fitness_tracker.entity.GoalType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "AI generated recommendation based on user's fitness activity")
public class RecommendationResponse {

    @Schema(description = "Current progress percentage towards the user's goal", example = "60")
    private double progress;

    @Schema(description = "Encouragement message for the user", example = "Great effort! Keep pushing forward.")
    private String encouragement;

    @Schema(description = "Warning message if user activity is low", example = "Stay consistent with your daily activities.")
    private String warning;

    @Schema(description = "AI generated advice to improve progress", example = "Even 10 minutes of daily exercise can make a big difference.")
    private String advice;

}
