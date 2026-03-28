package com.sree.fitness_tracker.controller;

import com.sree.fitness_tracker.dto.request.LogActivityRequest;
import com.sree.fitness_tracker.dto.response.ActivityResponse;
import com.sree.fitness_tracker.dto.response.MessageResponse;
import com.sree.fitness_tracker.service.ActivityService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
@Tag(name = "Activity Tracking", description = "Daily activity logs")
@RestController
@RequestMapping("/api/activities")
@RequiredArgsConstructor
public class ActivityController {

    private final ActivityService activityService;


    @Operation(summary = "Log daily activity")
    @SecurityRequirement(name = "bearerAuth")
    @PostMapping
    public ActivityResponse logActivity(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody LogActivityRequest request) {

        String email = userDetails.getUsername();

        return activityService.logDailyActivity(email, request);
    }

    @GetMapping
    @Operation(summary = "Get user dailyActivity")
    public List<ActivityResponse> getUserActivities(@AuthenticationPrincipal UserDetails userDetails) {
        String email = userDetails.getUsername();
        return activityService.getActivityHistory(email);
    }

    @GetMapping("/date")
    @Operation(summary = "get daily activity by Date")
    public ActivityResponse getActivityByDate(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestParam LocalDate date) {

        String email = userDetails.getUsername();

        return activityService.getActivityByDate(email, date);
    }

    @DeleteMapping("/{activityId}")
    @Operation(summary = "Delete an activity")
    public MessageResponse deleteActivity(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable Long activityId) {

        String email = userDetails.getUsername();

        return activityService.deleteActivity(email, activityId);
    }
}