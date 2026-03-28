package com.sree.fitness_tracker.repository;

import com.sree.fitness_tracker.entity.BodyMetric;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BodyMetricRepository extends JpaRepository<BodyMetric, Long> {

    List<BodyMetric> findByUser_Id(Long userId);

}
