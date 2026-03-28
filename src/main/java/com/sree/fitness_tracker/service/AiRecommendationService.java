package com.sree.fitness_tracker.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sree.fitness_tracker.entity.DailyActivity;
import com.sree.fitness_tracker.entity.Goal;
import com.sree.fitness_tracker.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AiRecommendationService {


    private final RestTemplate restTemplate ;

    @Value("${gemini.api.key}")
    private String apiKey;



    public String buildPrompt(User user,
                              Goal goal,
                              List<DailyActivity> activities,
                              double progress) {

        StringBuilder prompt = new StringBuilder();

        prompt.append("User: ")
                .append(user.getUsername())
                .append("\n");

        prompt.append("User goal: ")
                .append(goal.getGoalType())
                .append("\n");

        prompt.append("Progress: ")
                .append(progress)
                .append("%\n");

        prompt.append("Last 7 days activity:\n");

        for (DailyActivity activity : activities) {
            prompt.append(activity.getDate())
                    .append(" - ")
                    .append(activity.getActivityStatus())
                    .append("\n");
        }

        prompt.append("\nGive motivational advice, warnings if progress is poor, and improvement suggestions" +
                "in 20-30 words max. Keep the messages warm and friendly.");

        return prompt.toString();
    }

    public String getRecommendation(String prompt) throws Exception {

        String url =
                "https://generativelanguage.googleapis.com/v1/models/gemini-2.5-flash:generateContent?key="
                        + apiKey;

        Map<String, Object> textPart = Map.of(
                "text", prompt
        );

        Map<String, Object> part = Map.of(
                "parts", List.of(textPart)
        );

        Map<String, Object> body = Map.of(
                "contents", List.of(part)
        );

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, Object>> request =
                new HttpEntity<>(body, headers);

        ResponseEntity<String> response =
                restTemplate.postForEntity(url, request, String.class);


        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(response.getBody());
        String advice = root
                .path("candidates")
                .get(0)
                .path("content")
                .path("parts")
                .get(0)
                .path("text")
                .asText();

        advice = advice
                .replace("\n", " ")
                .replace("*", "")
                .trim();

        return advice;

    }
}