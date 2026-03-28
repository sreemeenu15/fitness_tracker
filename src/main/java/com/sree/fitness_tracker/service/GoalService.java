package com.sree.fitness_tracker.service;

import com.sree.fitness_tracker.dto.request.CreateGoalRequest;
import com.sree.fitness_tracker.dto.request.UpdateGoalRequest;
import com.sree.fitness_tracker.dto.response.MessageResponse;
import com.sree.fitness_tracker.dto.response.GoalResponse;

import java.util.List;

public interface GoalService {

    GoalResponse createGoal(String email, CreateGoalRequest request);

    GoalResponse updateGoal(Long goalId, UpdateGoalRequest request);

    List<GoalResponse> getUserGoals(String email);

    MessageResponse deleteGoal(String email,Long goalId);

}