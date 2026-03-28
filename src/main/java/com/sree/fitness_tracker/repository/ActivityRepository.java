package com.sree.fitness_tracker.repository;

import com.sree.fitness_tracker.entity.DailyActivity;
import com.sree.fitness_tracker.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ActivityRepository extends JpaRepository<DailyActivity, Long> {



//    List<DailyActivity> findByUserId(Long userId);

    Optional<DailyActivity> findByUserEmailAndDate(String email, LocalDate date);

    List<DailyActivity> findByUserEmail(String email);
    List<DailyActivity> findByUserAndDateBetween(
            User user,
            LocalDate startDate,
            LocalDate endDate
    );
}
