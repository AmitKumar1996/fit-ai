package com.fitness.ActivityService.Controller;


import com.fitness.ActivityService.dto.ActivityRequest;
import com.fitness.ActivityService.dto.ActivityResponce;
import com.fitness.ActivityService.service.ActivityService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/activities")
@AllArgsConstructor
@NoArgsConstructor
public class ActivityController {

    private ActivityService activityService;

    @PostMapping()
    public ResponseEntity<ActivityResponce> trackActivity(@RequestBody ActivityRequest request){
        return  ResponseEntity.ok(activityService.trackActivity(request));
    }

}

