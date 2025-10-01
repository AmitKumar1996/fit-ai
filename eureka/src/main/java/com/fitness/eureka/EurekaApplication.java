package com.fitness.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaApplication {

	// ANSI color codes
	public static final String RESET = "\u001B[0m";
	public static final String GREEN = "\u001B[32m";
	public static final String YELLOW = "\u001B[33m";
	public static final String RED = "\u001B[31m";
	public static final String BLUE = "\u001B[34m";
	public static final String CYAN = "\u001B[36m";
	public static final String PURPLE = "\u001B[35m";

	// Prints Microservices System Design
	private static void printSystemDesign() {
		System.out.println();
		System.out.println(PURPLE + "╔════════════════════════════════════════════════════════╗" + RESET);
		System.out.println(PURPLE + "║" + CYAN + "            MICROSERVICES ARCHITECTURE DESIGN           " + PURPLE + "║" + RESET);
		System.out.println(PURPLE + "╠════════════════════════════════════════════════════════╣" + RESET);

		System.out.println(GREEN + "  🧑‍💻 Client" + RESET + " --> " + YELLOW + "🌐 API Gateway" + RESET);
		System.out.println(YELLOW + "                |" + RESET);
		System.out.println(YELLOW + "         -----------------" + RESET);
		System.out.println(YELLOW + "         |       |       |" + RESET);
		System.out.println(GREEN + "     👤 User   🛒 Order   📦 Inventory" + RESET);
		System.out.println("         |       |       |");
		System.out.println(GREEN + "   🗄️ DBs / 🔁 Cache (Postgres/Redis)" + RESET);
		System.out.println("         |       |       |");
		System.out.println(GREEN + "     📨 Kafka / Messaging Queue" + RESET);
		System.out.println("         |       |       |");
		System.out.println(CYAN + "        🔎 Eureka Service Registry" + RESET);

		System.out.println(PURPLE + "╚════════════════════════════════════════════════════════╝" + RESET);
		System.out.println();
	}

	public static void main(String[] args) throws InterruptedException {
		String serviceName = "Fitness Eureka Service Registry";
		String activeProfile = "default"; // can be dynamically fetched
		String serverPort = "8761"; // Eureka default
		String dashboard = "http://localhost:8761";
		String registeredServices = "ActivityService, UserService, OrderService";

		// Start Spring Boot (Eureka)
		SpringApplication.run(EurekaApplication.class, args);

		// Print System Design Overview
		printSystemDesign();

		// Display Eureka Info Box
		System.out.println();
		System.out.println(PURPLE + "╔════════════════════════════════════════════════════════════════════════════════╗" + RESET);
		System.out.println(PURPLE + "║" + CYAN + "                    🔎  EUREKA SERVICE REGISTRY  📡             " + PURPLE + "        ║" + RESET);
		System.out.println(PURPLE + "╠════════════════════════════════════════════════════════════════════════════════╣" + RESET);
		System.out.println(GREEN + "🟢 Service Name   : " + RESET + serviceName);

		// Dynamic profile color
		String profileColor = switch (activeProfile.toLowerCase()) {
			case "prod" -> RED;
			case "dev" -> YELLOW;
			default -> BLUE;
		};
		System.out.println(GREEN + "🟢 Profile        : " + profileColor + activeProfile + RESET);
		System.out.println(GREEN + "📌 Server Port    : " + RESET + serverPort);
		System.out.println(GREEN + "📊 Dashboard URL  : " + RESET + dashboard);
		System.out.println(GREEN + "📡 Registered MS  : " + RESET + registeredServices);
		System.out.println(GREEN + "⚙️ Role           : " + RESET + "Service Discovery + Load Balancer");
		System.out.println(PURPLE + "╚════════════════════════════════════════════════════════════════════════════════╝" + RESET);
		System.out.println();

		// Animated Boot Loader
		System.out.println(CYAN + "🎉 Booting " + serviceName + "..." + RESET);
		int totalSteps = 20;
		for (int i = 1; i <= totalSteps; i++) {
			int progress = i * 100 / totalSteps;
			String bar = "▓".repeat(i) + "░".repeat(totalSteps - i);
			String spinner = switch (i % 4) {
				case 0 -> "|";
				case 1 -> "/";
				case 2 -> "-";
				default -> "\\";
			};
			System.out.print("\r" + GREEN + "[" + bar + "] " + progress + "% " + spinner + RESET);
			Thread.sleep(150);
		}

		// Final Boot Complete
		System.out.println("\n" + GREEN + "✅ Boot complete! " + CYAN + serviceName + " is up and running!" + RESET);
		System.out.println(CYAN + "👉 Open Dashboard at: " + YELLOW + dashboard + RESET);
		System.out.println(CYAN + "👉 Waiting for services to register..." + RESET);
	}
}
