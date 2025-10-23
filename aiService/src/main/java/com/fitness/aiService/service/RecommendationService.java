package com.fitness.aiService.service;

import com.fitness.aiService.repository.RecommendationRepository;
import lombok.RequiredArgsConstructor;
import com.fitness.aiService.model.Recommendation;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RecommendationService {

    private final RecommendationRepository recommendationRepository;

    public List<Recommendation> getUserRecommendation(String userId) {
        return recommendationRepository.findByUserId(userId);
    }

    public Recommendation getActivityRecommendation(String activityId) {
        return recommendationRepository.findByActivityId(activityId)
                .orElseGet(() -> createDefaultRecommendation(activityId));
    }

    private Recommendation createDefaultRecommendation(String activityId) {
        return Recommendation.builder()
                .activityId(activityId)
                .recommendation("No detailed analysis available")
                .improvements(Collections.singletonList("Continue with your current routine"))
                .suggestions(Collections.singletonList("No specific suggestions"))
                .safety(Arrays.asList(
                        "Always warm up before exercise",
                        "Stay hydrated",
                        "Listen to your body"
                ))
                .createdAt(LocalDateTime.now())
                .build();
    }
}
