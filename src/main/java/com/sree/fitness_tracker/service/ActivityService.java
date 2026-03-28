package com.sree.fitness_tracker.service;

import com.sree.fitness_tracker.dto.request.LogActivityRequest;
import com.sree.fitness_tracker.dto.response.ActivityResponse;
import com.sree.fitness_tracker.dto.response.MessageResponse;

import java.time.LocalDate;
import java.util.List;

public interface ActivityService {

    ActivityResponse logDailyActivity(String email, LogActivityRequest request);

    ActivityResponse getActivityByDate(String email, LocalDate date);

    List<ActivityResponse> getActivityHistory(String email);

    MessageResponse deleteActivity(String email,Long activityId);

}
