package com.fitness.ActivityService.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;


//In JPA / Hibernate → we use @Entity to tell the ORM: “this class is a database entity, map it to a table.

//In Spring Data MongoDB → we use @Document to tell Spring: “this class is a MongoDB document, map it to a collection.

//@Document(collation ="ai-fit")
@Document(collection = "activities")
@Data
@Builder        //  a chainable, flexible, human-friendly object creator.
@AllArgsConstructor
@NoArgsConstructor
public class Activity {

    private String id;
    private  String userId;
    private  ActivityType type;
    private  Integer duration;
    private LocalDateTime starTime;

    private Integer caloriesBurned;

    @Field("matrics")
    private Map<String, Object> additionalMatrics;
    @LastModifiedDate
    private  LocalDateTime upadatedAt;

    @CreatedDate
    private  LocalDateTime createdAt;


}


