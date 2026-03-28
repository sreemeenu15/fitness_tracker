package com.sree.fitness_tracker.service;

import com.sree.fitness_tracker.dto.request.LogBodyMetricRequest;
import com.sree.fitness_tracker.dto.request.UpdateBodyMetricRequest;
import com.sree.fitness_tracker.dto.response.BodyMetricResponse;

import java.util.List;

public interface BodyMetricService {

    BodyMetricResponse logMetric(Long userId, LogBodyMetricRequest request);

    List<BodyMetricResponse> getMetrics(Long userId);

    BodyMetricResponse updateMetric(Long metricId, UpdateBodyMetricRequest request);

}
