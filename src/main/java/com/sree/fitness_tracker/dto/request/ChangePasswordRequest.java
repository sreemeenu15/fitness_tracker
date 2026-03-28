package com.sree.fitness_tracker.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Changing user password")
public class ChangePasswordRequest {

    @Schema(description = "oldPassword", example = "date@234Sree")
    private String oldPassword;

    @Schema(description = "NewPassword", example = "date@234Sree")
    private String newPassword;

}
