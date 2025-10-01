package com.fitness.ActivityService.dto;


import com.fitness.ActivityService.model.ActivityType;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;

@Data
public class ActivityResponce {
    private String id;
    private  String userId;
    private ActivityType type;
    private  Integer duration;
    private LocalDateTime starTime;
    private Integer caloriesBurned;
    private Map<String, Object> additionalMatrics;
    private  LocalDateTime upadatedAt;
    private  LocalDateTime createdAt;
}
