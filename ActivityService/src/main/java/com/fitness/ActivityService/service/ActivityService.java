package com.fitness.ActivityService.service;


import com.fitness.ActivityService.dto.ActivityRequest;
import com.fitness.ActivityService.dto.ActivityResponce;
import com.fitness.ActivityService.model.ActivityType;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;

@Service
public class ActivityService {

    public ActivityResponce trackActivity(ActivityRequest request) {
    }
}
