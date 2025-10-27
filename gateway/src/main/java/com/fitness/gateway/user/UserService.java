package com.fitness.gateway.user;

import com.fitness.gateway.RegisterRequest;
import com.fitness.gateway.UserResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientRequestException;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private static final String RED = "\u001B[31m";
    private static final String GREEN = "\u001B[32m";
    private static final String YELLOW = "\u001B[33m";
    private static final String BLUE = "\u001B[34m";
    private static final String PURPLE = "\u001B[35m";
    private static final String CYAN = "\u001B[36m";
    private static final String RESET = "\u001B[0m";

    private final WebClient userServiceWebClient;

    /**
     * Validate if a user exists by calling the User Service
     */
    public Mono<Boolean> validateUser(String userId) {
        log.info(RED + "C" + GREEN + "a" + YELLOW + "l" + BLUE + "l" + PURPLE + "i" + CYAN + "n" + RED + "g " +
                GREEN + "U" + YELLOW + "s" + BLUE + "e" + PURPLE + "r " + CYAN + "S" + RED + "e" +
                GREEN + "r" + YELLOW + "v" + BLUE + "i" + PURPLE + "c" + CYAN + "e" +
                RESET + " for userId={}", userId);

        return userServiceWebClient.get()
                .uri("/api/users/{userId}/validate", userId)
                .retrieve()
                .bodyToMono(Boolean.class)
                .onErrorResume(WebClientResponseException.class, e -> {
                    if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                        log.warn("User not found: {}", userId);
                        return Mono.just(false);
                    } else if (e.getStatusCode() == HttpStatus.BAD_REQUEST) {
                        log.error("Invalid User ID: {}", userId);
                        return Mono.error(new RuntimeException("Invalid User ID: " + userId));
                    } else {
                        log.error("Unexpected response error: {}", e.getMessage());
                        return Mono.error(new RuntimeException("Unexpected error: " + e.getMessage()));
                    }
                })
                .onErrorResume(WebClientRequestException.class, e -> {
                    log.error("Request error while calling User Service for {}: {}", userId, e.getMessage());
                    return Mono.error(new RuntimeException("User Service unreachable: " + e.getMessage()));
                });
    }

    /**
     * Register a new user through User Service
     */
    public Mono<UserResponse> registerUser(RegisterRequest registerRequest) {
        log.info(RED + "C" + GREEN + "a" + YELLOW + "l" + BLUE + "l" + PURPLE + "i" + CYAN + "n" + RED + "g " +
                GREEN + "U" + YELLOW + "s" + BLUE + "e" + PURPLE + "r " +
                CYAN + "R" + RED + "e" + GREEN + "g" + YELLOW + "i" + BLUE + "s" + PURPLE + "t" +
                CYAN + "r" + RED + "a" + GREEN + "t" + YELLOW + "i" + BLUE + "o" + PURPLE + "n" +
                RESET + " for {}", registerRequest.getEmail());

        return userServiceWebClient.post()
                .uri("/api/users/register")
                .bodyValue(registerRequest)
                .retrieve()
                .bodyToMono(UserResponse.class)
                .onErrorResume(WebClientResponseException.class, e -> {
                    if (e.getStatusCode() == HttpStatus.BAD_REQUEST) {
                        log.error("Bad request while registering user {}: {}", registerRequest.getEmail(), e.getMessage());
                        return Mono.error(new RuntimeException("Invalid registration request: " + e.getMessage()));
                    } else {
                        log.error("Unexpected response while registering user: {}", e.getMessage());
                        return Mono.error(new RuntimeException("Unexpected error: " + e.getMessage()));
                    }
                })
                .onErrorResume(WebClientRequestException.class, e -> {
                    log.error("Request error while calling User Service for {}: {}", registerRequest.getEmail(), e.getMessage());
                    return Mono.error(new RuntimeException("User Service unreachable: " + e.getMessage()));
                });
    }
}
