package com.sree.fitness_tracker.service;

import com.sree.fitness_tracker.dto.response.RecommendationResponse;
import com.sree.fitness_tracker.entity.DailyActivity;
import com.sree.fitness_tracker.entity.Goal;
import com.sree.fitness_tracker.entity.User;
import com.sree.fitness_tracker.repository.ActivityRepository;
import com.sree.fitness_tracker.repository.GoalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RecommendationServiceImpl implements RecommendationService {

    private final GoalRepository goalRepository;
    private final ProgressService progressService;
    private final ActivityRepository activityRepository;
    private final AiRecommendationService aiRecommendationService;

    @Override
    public RecommendationResponse generateDailyRecommendation(User user) {

        LocalDate today = LocalDate.now();
        LocalDate sevenDaysAgo = today.minusDays(7);

        List<DailyActivity> activities =
                activityRepository.findByUserAndDateBetween(user, sevenDaysAgo, today);

        List<Goal> goals = goalRepository.findByUser(user);

        if (goals.isEmpty()) {
            return RecommendationResponse.builder()
                    .progress(0)
                    .encouragement("Let's start your fitness journey!")
                    .warning("You haven't set any goals yet.")
                    .advice("Create a goal to start receiving personalized recommendations.")
                    .build();
        }

        Goal goal = goals.get(0);

        double progress = progressService.calculateGoalProgress(user, goal);

        String prompt = aiRecommendationService.buildPrompt(
                user,
                goal,
                activities,
                progress
        );

        String recommendation;

        try {
            recommendation = aiRecommendationService.getRecommendation(prompt);
        } catch (Exception e) {
            recommendation = "AI service temporarily unavailable.";
        }
        return  RecommendationResponse.builder()
                .progress(progress)
                .encouragement("Great effort! Keep pushing forward.")
                .warning("Stay consistent with your daily activities.")
                .advice(recommendation)
                .build();
    }
}