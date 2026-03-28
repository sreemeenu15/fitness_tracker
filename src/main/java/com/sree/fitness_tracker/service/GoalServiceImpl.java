package com.sree.fitness_tracker.service;

import com.sree.fitness_tracker.entity.Goal;
import com.sree.fitness_tracker.entity.User;
import com.sree.fitness_tracker.repository.GoalRepository;
import com.sree.fitness_tracker.dto.request.CreateGoalRequest;
import com.sree.fitness_tracker.dto.request.UpdateGoalRequest;
import com.sree.fitness_tracker.dto.response.GoalResponse;
import com.sree.fitness_tracker.dto.response.MessageResponse;

import com.sree.fitness_tracker.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GoalServiceImpl implements GoalService {

    private final GoalRepository goalRepository;
    private final UserRepository userRepository;



    @Override
    public GoalResponse createGoal(String email, CreateGoalRequest request) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Goal goal = new Goal();

        goal.setUser(user);
        goal.setGoalType(request.getGoalType());
        goal.setTargetValue(request.getTargetValue());
        goal.setStartDate(request.getStartDate());
        goal.setEndDate(request.getEndDate());

        goalRepository.save(goal);

        return mapToGoalResponse(goal);
    }
    private GoalResponse mapToGoalResponse(Goal goal) {

        GoalResponse response = new GoalResponse();

        response.setId(goal.getId());
        response.setGoalType(goal.getGoalType());
        response.setTargetValue(goal.getTargetValue());
        response.setStartDate(goal.getStartDate());
        response.setEndDate(goal.getEndDate());

        return response;
    }
    @Override
    public GoalResponse updateGoal(Long goalId, UpdateGoalRequest request) {

        Goal goal = goalRepository.findById(goalId)
                .orElseThrow(() -> new RuntimeException("Goal not found"));

        goal.setTargetValue(request.getTargetValue());
        goal.setEndDate(request.getEndDate());

        goalRepository.save(goal);

        return mapToGoalResponse(goal);
    }

    @Override
    public List<GoalResponse> getUserGoals(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<Goal> goals = goalRepository.findByUser(user);

        return goals.stream()
                .map(this::mapToGoalResponse)
                .toList();
    }

    @Override
    public MessageResponse deleteGoal(String email, Long goalId) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Goal goal = goalRepository.findById(goalId)
                .orElseThrow(() -> new RuntimeException("Goal not found"));

        if (!goal.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Unauthorized to delete this goal");
        }

        goalRepository.delete(goal);

        return new MessageResponse("Goal deleted successfully");
    }

}
