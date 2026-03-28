package com.sree.fitness_tracker.service;

import com.sree.fitness_tracker.dto.request.LogBodyMetricRequest;
import com.sree.fitness_tracker.dto.request.UpdateBodyMetricRequest;
import com.sree.fitness_tracker.dto.response.BodyMetricResponse;
import com.sree.fitness_tracker.entity.BodyMetric;
import com.sree.fitness_tracker.entity.User;
import com.sree.fitness_tracker.repository.BodyMetricRepository;
import com.sree.fitness_tracker.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BodyMetricServiceImpl implements BodyMetricService {

    private final BodyMetricRepository bodyMetricRepository;
    private final UserRepository userRepository;

    @Override
    public BodyMetricResponse logMetric(Long userId, LogBodyMetricRequest request) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        BodyMetric metric = new BodyMetric();

        metric.setUser(user);
        metric.setWeight(request.getWeight());
        metric.setHeight(request.getHeight());
        metric.setBodyFatPercentage(request.getBodyFatPercentage());
        metric.setLoggedDate(request.getLoggedDate());

        bodyMetricRepository.save(metric);

        return mapToBodyMetricResponse(metric);
    }

    @Override
    public List<BodyMetricResponse> getMetrics(Long userId) {

        List<BodyMetric> metrics = bodyMetricRepository.findByUser_Id(userId);

        return metrics.stream()
                .map(this::mapToBodyMetricResponse)
                .toList();
    }

    @Override
    public BodyMetricResponse updateMetric(Long metricId, UpdateBodyMetricRequest request) {

        BodyMetric metric = bodyMetricRepository.findById(metricId)
                .orElseThrow(() -> new RuntimeException("Body metric not found"));

        metric.setWeight(request.getWeight());
        metric.setHeight(request.getHeight());
        metric.setBodyFatPercentage(request.getBodyFatPercentage());
        metric.setLoggedDate(request.getLoggedDate());

        bodyMetricRepository.save(metric);

        return mapToBodyMetricResponse(metric);
    }

    private BodyMetricResponse mapToBodyMetricResponse(BodyMetric metric) {

        BodyMetricResponse response = new BodyMetricResponse();

        response.setId(metric.getId());
        response.setWeight(metric.getWeight());
        response.setHeight(metric.getHeight());
        response.setBodyFatPercentage(metric.getBodyFatPercentage());
        response.setLoggedDate(metric.getLoggedDate());

        return response;
    }
}
