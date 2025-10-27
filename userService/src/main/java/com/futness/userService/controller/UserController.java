package com.futness.userService.controller;

import com.futness.userService.dto.RegisterRequest;
import com.futness.userService.dto.UserResponse;
import com.futness.userService.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/users")
@RestController
@AllArgsConstructor
@Slf4j
public class UserController {

    private UserService userService;
     @GetMapping("/{userId}")
    public ResponseEntity<UserResponse> getUserProfile(@PathVariable String userId){
        return ResponseEntity.ok(userService.getUserProfile(userId));
    }


    @GetMapping("/{userId}/validate")
    public ResponseEntity<Boolean> validateUser(@PathVariable String userId){
        // System.out.println("User Id"+""+userId);
        log.info("User Id {}", userId);
        return ResponseEntity.ok(userService
                .existsByKeycloakId (userId));
    }



    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@Valid @RequestBody RegisterRequest request){

        return  ResponseEntity.ok(userService.register(request));
    }


    








}
