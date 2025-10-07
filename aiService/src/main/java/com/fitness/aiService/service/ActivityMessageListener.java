package com.fitness.aiService.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.fitness.aiService.model.Activity;  // ✅ Corrected import

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
@Service
@Slf4j
@RequiredArgsConstructor
public class ActivityMessageListener {

    private final ActivityAIService activityAIService;

    @KafkaListener(topics = "${kafka.topic.name}", groupId = "activity-processor-group")
    public void processActivity(Activity activity) {
        log.info("Received Activity for processing: {}", activity.getUserId());
        activityAIService.genrateRecommendation(activity);
    }
}
