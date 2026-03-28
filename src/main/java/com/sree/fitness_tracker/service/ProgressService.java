package com.sree.fitness_tracker.service;

import com.sree.fitness_tracker.entity.Goal;
import com.sree.fitness_tracker.entity.User;

public interface ProgressService {

    double calculateGoalProgress(User user, Goal goal);
}
