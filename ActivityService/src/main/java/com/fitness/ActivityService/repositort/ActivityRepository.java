package com.fitness.ActivityService.repositort;

import com.fitness.ActivityService.model.Activity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ActivityRepository extends MongoRepository<Activity, String> {

}
