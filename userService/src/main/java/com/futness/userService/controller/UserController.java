package com.futness.userService.controller;

import com.futness.userService.dto.RegisterRequest;
import com.futness.userService.dto.UserResponse;
import com.futness.userService.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/users")
@RestController
@AllArgsConstructor
public class UserController {

    private UserService userService;
     @GetMapping("/{userId}")
    public ResponseEntity<UserResponse> getUserProfile(@PathVariable String userId){
        return ResponseEntity.ok(userService.getUserProfile(userId));
    }



    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@Valid @RequestBody RegisterRequest request){

        return  ResponseEntity.ok(userService.register(request));
    }


    








}
