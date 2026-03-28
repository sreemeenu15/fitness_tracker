package com.sree.fitness_tracker.repository;

import com.sree.fitness_tracker.entity.Goal;
import com.sree.fitness_tracker.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GoalRepository extends JpaRepository<Goal, Long> {


    List<Goal> findByUser(User user);
}
