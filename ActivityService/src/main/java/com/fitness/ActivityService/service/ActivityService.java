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

@Service
@RequiredArgsConstructor
public class ActivityService {

    @Autowired
    private final ActivityRepository activityRepository;
    @Autowired
    private final UserValidationService userValidationService;

    private final KafkaTemplate<String, Activity> kafkaTemplate;
    @Value("${kafka.topic.name}")
    private  String topicName;



    public ActivityResponce trackActivity(ActivityRequest request) {

        boolean isValidUser = userValidationService.validateUser(request.getUserId());

        if (!isValidUser) {
            throw new RuntimeException("Invalid User:" + request.getUserId());
        }

        System.out.println("test2:->>>>>>" + request);

        Activity activity = Activity.builder()

                .userId(request.getUserId())
                .type(request.getType())
                .duration(request.getDuration())
                .caloriesBurned(request.getCaloriesBurned())
                .starTime(request.getStarTime())
                .additionalMatrics(request.getAdditionalMatrics()).build();

        Activity savedActivity = activityRepository.save(activity);
        try{
            kafkaTemplate.send(topicName, savedActivity.getUserId(), savedActivity);
        }
        catch (Exception e){
            e.printStackTrace();
        }


        System.out.println("test2:->>>>>>" + savedActivity);
        return mapToResponse(savedActivity);


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
