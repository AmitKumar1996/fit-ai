package com.fitness.configserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication

@EnableConfigServer
public class ConfigserverApplication {

	public static void main(String[] args) {

		SpringApplication.run(ConfigserverApplication.class, args);
		System.out.println("Jay shri Ram!");
	}

}
