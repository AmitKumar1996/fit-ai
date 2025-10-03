package com.fitness.aiService.service;

import com.fitness.aiService.repository.RecommendationRepository;
import lombok.RequiredArgsConstructor;
import model.Recommendation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecommendationService {

    @Autowired
    private final RecommendationRepository recommendationRepository;


    public List<Recommendation> getUserRecommendation(String userId) {
        return recommendationRepository.findByUserId(userId);
    }

    public Recommendation getActivityRecommendation(String activityId) {

        return recommendationRepository.findByActivityId(activityId);
    }
}
