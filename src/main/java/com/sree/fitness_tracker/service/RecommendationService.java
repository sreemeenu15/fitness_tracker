package com.sree.fitness_tracker.service;

import com.sree.fitness_tracker.dto.response.RecommendationResponse;
import com.sree.fitness_tracker.entity.User;

public interface RecommendationService {


   RecommendationResponse generateDailyRecommendation(User user);

}
