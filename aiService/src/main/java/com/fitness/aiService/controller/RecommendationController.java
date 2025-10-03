package com.fitness.aiService.controller;

import com.fitness.aiService.service.RecommendationService;
import lombok.RequiredArgsConstructor;
import model.Recommendation;
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


    private final RecommendationService recommendationService;

    @GetMapping("/user/{UserId}")
    public ResponseEntity<List<Recommendation>> getUserRecommendation(@PathVariable String UserId) {
        return ResponseEntity.ok(recommendationService.getUserRecommendation(UserId));
    }


    @GetMapping("/activity/{activityId}")
    public ResponseEntity<Recommendation>
    getActivityRecommendation(@PathVariable String activityId) {

        return ResponseEntity.ok(recommendationService.getActivityRecommendation(activityId));


    }


}
