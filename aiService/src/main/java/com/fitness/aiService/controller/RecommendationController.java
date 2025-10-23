package com.fitness.aiService.controller;

import com.fitness.aiService.service.RecommendationService;
import lombok.RequiredArgsConstructor;
import com.fitness.aiService.model.Recommendation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/recommendations")

public class RecommendationController {
    @Autowired
    private RecommendationService recommendationService; // no @Autowired

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Recommendation>> getUserRecommendation(@PathVariable String userId) {

        System.out.println("AI is Running...");

        return ResponseEntity.ok(recommendationService.getUserRecommendation(userId));
    }

    @GetMapping("/activity/{activityId}")
    public ResponseEntity<Recommendation> getActivityRecommendation(@PathVariable String activityId) {


        System.out.println("AI is Running..."+activityId);
        return ResponseEntity.ok(recommendationService.getActivityRecommendation(activityId));
    }
}
