package com.fitness.ActivityService.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientRequestException;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserValidationService {
private final WebClient userServiceWebClient;




    private static final String RED = "\u001B[31m";
    private static final String GREEN = "\u001B[32m";
    private static final String YELLOW = "\u001B[33m";
    private static final String BLUE = "\u001B[34m";
    private static final String PURPLE = "\u001B[35m";
    private static final String CYAN = "\u001B[36m";
    private static final String RESET = "\u001B[0m";

public boolean validateUser(String userId){
  //log.info("Calling User Service for {}", userId);


    log.info(RED + "C" + GREEN + "a" + YELLOW + "l" + BLUE + "l" + PURPLE + "i" + CYAN + "n" + RED + "g " +
            GREEN + "U" + YELLOW + "s" + BLUE + "e" + PURPLE + "r " +
            CYAN + "S" + RED + "e" + GREEN + "r" + YELLOW + "v" + BLUE + "i" + PURPLE + "c" + CYAN + "e" +
            RESET + " for {}", userId);
    try{
        return userServiceWebClient.get()
                .uri("/api/users/{userId}/validate", userId)
                .retrieve()
                .bodyToMono(Boolean.class)
                .block();
    }
    catch (WebClientRequestException e){
        e.printStackTrace();

    }
return  false;
}


}
