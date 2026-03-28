package com.sree.fitness_tracker.controller;

import com.sree.fitness_tracker.dto.response.RecommendationResponse;
import com.sree.fitness_tracker.entity.User;
import com.sree.fitness_tracker.service.RecommendationService;

import com.sree.fitness_tracker.service.UserService;
import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;

@Tag(name = "AI Recommendations", description = "Fitness recommendations and insights")
@RestController
@RequestMapping("/api/recommendations")
@RequiredArgsConstructor

public class RecommendationController {

    private final RecommendationService recommendationService;
    private final UserService userService;
    private final Bucket bucket;
    @Autowired
    public RecommendationController(RecommendationService recommendationService,
                                    UserService userService) {

        this.recommendationService = recommendationService;
        this.userService = userService;

        this.bucket = Bucket.builder()
                .addLimit(Bandwidth.simple(10, Duration.ofMinutes(1)))
                .build();
    }

    @Operation(summary = "Get recommendation for user")
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping
    public RecommendationResponse getRecommendation(@AuthenticationPrincipal UserDetails userDetails) {


        if (!bucket.tryConsume(1)) {
            throw new RuntimeException("Too many requests. Try again later.");
        }


        String email = userDetails.getUsername();

        User user = userService.getUserEntityByEmail(email);

        return recommendationService.generateDailyRecommendation(user);
    }
}