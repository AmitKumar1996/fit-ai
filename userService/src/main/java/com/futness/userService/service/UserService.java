package com.futness.userService.service;

import com.futness.userService.UserRepository;
import com.futness.userService.dto.RegisterRequest;
import com.futness.userService.dto.UserResponse;
import com.futness.userService.model.User;
import com.futness.userService.model.UserRole;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class UserService {

    @Autowired
    private UserRepository repository;

    public UserResponse register(RegisterRequest request){

        if(repository.existsByEmail(request.getEmail())){
            throw  new RuntimeException("Email Alrady Exist");
        }
        User user= new User();
        user.setEmail(request.getEmail());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setPassword(request.getPassword());

        User savedUser =new User();
        UserResponse response=new UserResponse();
        response.setId(savedUser.getId());
        response.setPassword(savedUser.getPassword());
        response.setEmail(savedUser.getEmail());
        response.setFirstName(savedUser.getFirstName());
        response.setLastName(savedUser.getLastName());
        response.setCreatedAt(savedUser.getCreatedAt());
        response.setUpdatedAt(savedUser.getUpdatedAt());
        return new UserResponse();
    }
}
