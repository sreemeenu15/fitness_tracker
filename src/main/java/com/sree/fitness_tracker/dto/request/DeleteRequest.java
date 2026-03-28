package com.sree.fitness_tracker.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "UserID to be deleted")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeleteRequest {
    @Schema(description = "userID ")
    private Long userId;

}