package com.futness.userService.service;

import com.futness.userService.dto.RegisterRequest;
import com.futness.userService.dto.UserResponse;
import com.futness.userService.exception.EmailAlreadyExistsException;
import com.futness.userService.model.User;
import com.futness.userService.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
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

    private static final String RED = "\u001B[31m";
    private static final String GREEN = "\u001B[32m";
    private static final String YELLOW = "\u001B[33m";
    private static final String BLUE = "\u001B[34m";
    private static final String PURPLE = "\u001B[35m";
    private static final String CYAN = "\u001B[36m";
    private static final String RESET = "\u001B[0m";




    public Boolean existByUserId(String userId) {
     //   log.info("Calling User Service for {}", userId);

        log.info(RED + "C" + GREEN + "a" + YELLOW + "l" + BLUE + "l" + PURPLE + "i" + CYAN + "n" + RED + "g " +
                GREEN + "U" + YELLOW + "s" + BLUE + "e" + PURPLE + "r " +
                CYAN + "S" + RED + "e" + GREEN + "r" + YELLOW + "v" + BLUE + "i" + PURPLE + "c" + CYAN + "e" +
                RESET + " for {}", userId);
        return repository.existsById(userId);
    }
}
