package com.fitness.ActivityService.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;

@Document(collation ="activities")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Activity {

    private String id;
    private  String userId;
    private  ActivityType type;
    private  Integer duration;
    private LocalDateTime starTime;

    @Field("matrics")
    private Map<String, Objects> additionalMatrix;
    @LastModifiedDate
    private  LocalDateTime upadatedAt;

    @CreatedDate
    private  LocalDateTime createdAt;


}


