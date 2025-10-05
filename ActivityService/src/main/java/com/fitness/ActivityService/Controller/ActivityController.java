package com.fitness.ActivityService.Controller;

import com.fitness.ActivityService.dto.ActivityRequest;
import com.fitness.ActivityService.dto.ActivityResponce;
import com.fitness.ActivityService.service.ActivityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j; // ✅ @Slf4j import
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j // ✅ Logger automatically create ho jayega
@RestController
@RequestMapping("/api/activities")
@RequiredArgsConstructor
public class ActivityController {

    private final ActivityService activityService;

    // 🎨 ANSI color codes for colorful logs
    private static final String BLUE = "\u001B[34m";
    private static final String GREEN = "\u001B[32m";
    private static final String YELLOW = "\u001B[33m";
    private static final String CYAN = "\u001B[36m";
    private static final String RESET = "\u001B[0m";

    @PostMapping
    public ResponseEntity<ActivityResponce> trackActivity(@RequestBody ActivityRequest request) {

        // 🟩 Step 1️⃣ : Request receive hone par log
        log.info(BLUE + "🟩 Step 1: Received ActivityRequest -> userId={}, type={}, duration={}" + RESET,
                request.getUserId(), request.getType(), request.getDuration());

        // 🟨 Step 2️⃣ : Service call karne se pehle log
        log.info(YELLOW + "🟨 Step 2: Sending request to ActivityService..." + RESET);

        ActivityResponce response = activityService.trackActivity(request);

        // 🟦 Step 3️⃣ : Service se response milne par log
        log.info(CYAN + "🟦 Step 3: Response received from ActivityService for userId={} -> {}" + RESET,
                request.getUserId(), response);

        // 🟩 Step 4️⃣ : Client ko final response bhejne se pehle log
        log.info(GREEN + "🟩 Step 4: Sending final response to client for userId={}" + RESET,
                request.getUserId());

        return ResponseEntity.ok(response);
    }

}
