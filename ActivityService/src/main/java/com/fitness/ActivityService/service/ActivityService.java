package com.fitness.ActivityService.service;

import com.fitness.ActivityService.dto.ActivityRequest;
import com.fitness.ActivityService.dto.ActivityResponce;
import com.fitness.ActivityService.model.Activity;
import com.fitness.ActivityService.repositort.ActivityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ActivityService {

    @Autowired
    private final ActivityRepository activityRepository;
    @Autowired
    private final UserValidationService userValidationService;

    private final KafkaTemplate<String, Activity> kafkaTemplate;

    @Value("${kafka.topic.name}")
    private String topicName;

    public ActivityResponce trackActivity(ActivityRequest request) {

        // 🟢 Step 7: Start user validation
        log.info("🟢 Step 7️⃣ | Validating userId={} ...", request.getUserId());

        boolean isValidUser = userValidationService.validateUser(request.getUserId());

        if (!isValidUser) {
            // 🔴 Step 8: Invalid user case
            log.error("🔴 Step 8️⃣ | ❌ Invalid User Detected: {}", request.getUserId());
            throw new RuntimeException("Invalid User: " + request.getUserId());
        }

        // 🟠 Step 9: Valid user, preparing Activity entity
        log.info("🟠 Step 9️⃣ | ✅ User validation passed, preparing Activity entity for userId={}", request.getUserId());

        Activity activity = Activity.builder()
                .userId(request.getUserId())
                .type(request.getType())
                .duration(request.getDuration())
                .caloriesBurned(request.getCaloriesBurned())
                .starTime(request.getStarTime())
                .additionalMatrics(request.getAdditionalMatrics())
                .build();

        // 🔵 Step 10: Saving to DB
        log.info("🔵 Step 🔟 | 💾 Saving activity to DB for userId={}", request.getUserId());
        Activity savedActivity = activityRepository.save(activity);
        log.info("🔵 Step 🔟 | ✅ Activity saved successfully with ID={}", savedActivity.getId());

        try {
            // 🟣 Step 11: Sending data to Kafka
            log.info("🟣 Step 1️⃣1️⃣ | 🚀 Sending activity to Kafka topic='{}' for userId={}", topicName, savedActivity.getUserId());
            kafkaTemplate.send(topicName, savedActivity.getUserId(), savedActivity);
            log.info("🟣 Step 1️⃣1️⃣ | ✅ Kafka message sent successfully for activityId={}", savedActivity.getId());
        } catch (Exception e) {
            // 🔴 Step 12: Kafka send failed
            log.error("🔴 Step 1️⃣2️⃣ | ⚠️ Failed to send Kafka message for activityId={}", savedActivity.getId(), e);
        }

        // 🟤 Step 13: Mapping response object
        log.info("🟤 Step 1️⃣3️⃣ | 🧩 Mapping Activity entity to ActivityResponce for userId={}", request.getUserId());
        ActivityResponce response = mapToResponse(savedActivity);
        log.info("🟤 Step 1️⃣3️⃣ | ✅ Final ActivityResponce ready for userId={}: {}", request.getUserId(), response);

        return response;
    }

    private ActivityResponce mapToResponse(Activity activity) {
        ActivityResponce responce = new ActivityResponce();
        responce.setId(activity.getId());
        responce.setUserId(activity.getUserId());
        responce.setType(activity.getType());
        responce.setDuration(activity.getDuration());
        responce.setCaloriesBurned(activity.getCaloriesBurned());
        responce.setUpadatedAt(activity.getUpadatedAt());
        responce.setAdditionalMatrics(activity.getAdditionalMatrics());
        responce.setCreatedAt(activity.getCreatedAt());
        responce.setStarTime(activity.getStarTime());
        return responce;
    }
}
