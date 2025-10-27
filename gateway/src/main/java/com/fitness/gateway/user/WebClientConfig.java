package com.fitness.gateway.user;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * WebClientConfig is a configuration class for defining WebClient beans.
 * WebClient is a reactive, non-blocking HTTP client in Spring WebFlux.
 */
@Configuration
public class WebClientConfig {

    /**
     * Creates a WebClient.Builder bean that is load balanced.
     *
     * @LoadBalanced tells Spring Cloud to use Ribbon (or Spring Cloud LoadBalancer)
     * to resolve service names like "USERSERVICE" to actual instances
     * registered in Eureka.
     * <p>
     * This bean can be injected wherever you need a WebClient.Builder.
     */
    @Bean
    @LoadBalanced
    public WebClient.Builder webClientBuilder() {
        // Returns a builder to create WebClient instances
        return WebClient.builder();
    }

    /**
     * Creates a WebClient specifically configured to call the UserService.
     *
     * @param webClientBuilder the LoadBalanced WebClient.Builder injected by Spring
     * @return a WebClient instance ready to call UserService endpoints
     */
    @Bean
    public WebClient userServiceWebClient(WebClient.Builder webClientBuilder) {
        return webClientBuilder
                .baseUrl("http://USER-SERVICE") // USERSERVICE is the application name registered in Eureka
                .build();                       // Build the WebClient instance
    }

}
