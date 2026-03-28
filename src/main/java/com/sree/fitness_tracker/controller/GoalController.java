package com.sree.fitness_tracker.controller;


import com.sree.fitness_tracker.dto.request.CreateGoalRequest;
import com.sree.fitness_tracker.dto.request.UpdateGoalRequest;
import com.sree.fitness_tracker.dto.response.GoalResponse;
import com.sree.fitness_tracker.dto.response.MessageResponse;
import com.sree.fitness_tracker.service.GoalService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Tag(name = "Goal Management", description = "Manage fitness goals")
@RestController
@RequestMapping("/api/goals")
@RequiredArgsConstructor
public class GoalController {

    private final GoalService goalService;
    @Operation(summary = "Create new goal")
    @SecurityRequirement(name = "bearerAuth")
    @PostMapping
    public GoalResponse createGoal(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody CreateGoalRequest request) {
        String email = userDetails.getUsername();


        return goalService.createGoal(email, request);
    }

    @PutMapping("/{goalId}")
    @Operation(summary = "Update an existing goal")
    public GoalResponse updateGoal(
            @PathVariable Long goalId,
            @RequestBody UpdateGoalRequest request) {

        return goalService.updateGoal(goalId, request);
    }

    @GetMapping("/me/goals")
    @Operation(summary = "Gathering user goal")
    public List<GoalResponse> getUserGoals(@AuthenticationPrincipal UserDetails userDetails) {
        String email = userDetails.getUsername();
        return goalService.getUserGoals(email);
    }

    @DeleteMapping("/{goalId}")
    @Operation(summary = "Delete a fitness goal")
    public MessageResponse deleteGoal(@AuthenticationPrincipal UserDetails userDetails
            ,@PathVariable Long goalId) {

        String email = userDetails.getUsername();

        return goalService.deleteGoal(email,goalId);
    }
}