package com.fitness.ActivityService.dto;

import com.fitness.ActivityService.model.ActivityType;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;

@Data
public class ActivityRequest {

    private String userId;
    private ActivityType type;
    private Integer duration;
    private LocalDateTime starTime;
    private Integer caloriesBurned;
    private Map<String, Objects> additionalMatrics;
}
