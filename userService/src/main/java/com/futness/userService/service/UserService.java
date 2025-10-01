package com.futness.userService.service;

import com.futness.userService.dto.RegisterRequest;
import com.futness.userService.dto.UserResponse;
import com.futness.userService.exception.EmailAlreadyExistsException;
import com.futness.userService.model.User;
import com.futness.userService.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    @Autowired
    private UserRepository repository;

    public UserResponse register(RegisterRequest request){

        // Check if email already exists
        if(repository.existsByEmail(request.getEmail())) {
            throw new EmailAlreadyExistsException("Email already exists: " + request.getEmail());
        }

        User user = new User();
        user.setEmail(request.getEmail());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setPassword(request.getPassword());

        User savedUser = repository.save(user);

        UserResponse response = new UserResponse();
        response.setId(savedUser.getId());
        response.setPassword(savedUser.getPassword());
        response.setEmail(savedUser.getEmail());
        response.setFirstName(savedUser.getFirstName());
        response.setLastName(savedUser.getLastName());
        response.setCreatedAt(savedUser.getCreatedAt());
        response.setUpdatedAt(savedUser.getUpdatedAt());

        return response; // Return the populated response
    }
    public UserResponse getUserProfile(String userId){
        User user=repository.findById((userId)).orElseThrow(()->new RuntimeException("user not Found"));
        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setPassword(user.getPassword());
        response.setEmail(user.getEmail());
        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());
        response.setCreatedAt(user.getCreatedAt());
        response.setUpdatedAt(user.getUpdatedAt());

        return response; // Return the populated response
    }

    public Boolean existByUserId(String userId) {
        return repository.existsById(userId);
    }
}
