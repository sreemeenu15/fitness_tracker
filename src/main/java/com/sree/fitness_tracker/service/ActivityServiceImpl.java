package com.sree.fitness_tracker.service;

import com.sree.fitness_tracker.dto.request.LogActivityRequest;
import com.sree.fitness_tracker.dto.response.ActivityResponse;
import com.sree.fitness_tracker.dto.response.MessageResponse;
import com.sree.fitness_tracker.entity.DailyActivity;
import com.sree.fitness_tracker.entity.User;
import com.sree.fitness_tracker.repository.ActivityRepository;
import com.sree.fitness_tracker.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service

@RequiredArgsConstructor
public class ActivityServiceImpl implements ActivityService {

    private final ActivityRepository activityRepository;
    private final UserRepository userRepository;

    private User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public ActivityResponse logDailyActivity(String email, LogActivityRequest request) {


        User user = getUserByEmail(email);
        DailyActivity activity = activityRepository
                .findByUserEmailAndDate(email, request.getDate())
                .orElse(new DailyActivity());

        if (activity.getId() == null) {
            activity.setUser(user);
        }

        activity.setUser(user);
        activity.setDate(request.getDate());
        activity.setSleepHours(request.getSleepHours());
        activity.setWalkingSteps(request.getWalkingSteps());
        activity.setHydrationLitres(request.getHydrationLitres());
        activity.setExerciseMinutes(request.getExerciseMinutes());
        activity.setActivityStatus(request.getActivityStatus());

        activityRepository.save(activity);

        return mapToActivityResponse(activity);
    }

    @Override
    public ActivityResponse getActivityByDate(String email, LocalDate date) {

        DailyActivity activity = activityRepository
                .findByUserEmailAndDate(email, date)
                .orElseThrow(() -> new RuntimeException("Activity not found"));

        return mapToActivityResponse(activity);
    }

    @Override
    public List<ActivityResponse> getActivityHistory(String email) {

        List<DailyActivity> activities = activityRepository.findByUserEmail(email);

        return activities.stream()
                .map(this::mapToActivityResponse)
                .toList();
    }



    @Override
    public MessageResponse deleteActivity(String email, Long activityId) {

        User user = getUserByEmail(email);

        DailyActivity activity = activityRepository.findById(activityId)
                .orElseThrow(() -> new RuntimeException("Activity not found"));

        if (!activity.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Unauthorized");
        }

        activityRepository.delete(activity);

        return new MessageResponse("Activity deleted successfully");
    }

    private ActivityResponse mapToActivityResponse(DailyActivity activity) {

        ActivityResponse response = new ActivityResponse();

        response.setActivityStatus(activity.getActivityStatus());
        response.setDate(activity.getDate());
        response.setWalkingSteps(activity.getWalkingSteps());
        response.setExerciseMinutes(activity.getExerciseMinutes());
        response.setSleepHours(activity.getSleepHours());
        response.setHydrationLitres(activity.getHydrationLitres());

        return response;
    }
    }




