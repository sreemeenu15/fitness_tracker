package com.sree.fitness_tracker.service;

import com.sree.fitness_tracker.entity.ActivityStatus;
import com.sree.fitness_tracker.entity.DailyActivity;
import com.sree.fitness_tracker.entity.Goal;
import com.sree.fitness_tracker.entity.User;
import com.sree.fitness_tracker.repository.ActivityRepository;
import com.sree.fitness_tracker.repository.GoalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProgressServiceImpl implements ProgressService {

     private final GoalRepository goalRepository;
     private final ActivityRepository activityRepository;

    @Override
    public double calculateGoalProgress(User user, Goal goal) {

        List<DailyActivity> activities =
                activityRepository.findByUserAndDateBetween(
                        user,
                        goal.getStartDate(),
                        goal.getEndDate()
                );

        BigDecimal total = BigDecimal.ZERO;

        for (DailyActivity activity : activities) {

            if (activity.getActivityStatus() == ActivityStatus.MISSED) {
                total = total.subtract(BigDecimal.ONE); // penalty
                continue;
            }

            switch (goal.getGoalType()) {

                case STEPS:
                    if (activity.getWalkingSteps() != null)
                        total = total.add(BigDecimal.valueOf(activity.getWalkingSteps()));
                    break;

                case SLEEP_HOURS:
                    if (activity.getSleepHours() != null)
                        total = total.add(activity.getSleepHours());
                    break;

                case HYDRATION_LITRES:
                    if (activity.getHydrationLitres() != null)
                        total = total.add(activity.getHydrationLitres());
                    break;

                case EXERCISE_MINUTES:
                    if (activity.getExerciseMinutes() != null)
                        total = total.add(BigDecimal.valueOf(activity.getExerciseMinutes()));
                    break;

                default:
                    break;
            }
        }

        if (goal.getTargetValue() == null || goal.getTargetValue().doubleValue() == 0)
            return 0;

        double progress = total
                .divide(goal.getTargetValue(), 2, RoundingMode.HALF_UP)
                .multiply(BigDecimal.valueOf(100))
                .doubleValue();

        return progress;
    }


}
