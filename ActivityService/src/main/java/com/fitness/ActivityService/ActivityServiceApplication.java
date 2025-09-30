package com.fitness.ActivityService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ActivityServiceApplication {

	// New method: print microservices system design with emojis
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

		System.out.println(PURPLE + "╚════════════════════════════════════════════════════════╝" + RESET);
		System.out.println();
	}

	// ANSI color codes
	public static final String RESET = "\u001B[0m";
	public static final String GREEN = "\u001B[32m";
	public static final String YELLOW = "\u001B[33m";
	public static final String RED = "\u001B[31m";
	public static final String BLUE = "\u001B[34m";
	public static final String CYAN = "\u001B[36m";
	public static final String PURPLE = "\u001B[35m";

	public static void main(String[] args) throws InterruptedException {
		String serviceName = "Fitness Activity Service";
		String activeProfile = "default"; // can be dynamically fetched
		String serverPort = "8080";
		String dbURI = "mongodb://localhost:27017";
		String techStack = "IntelliJ → Java → Spring Boot → Postman → CI/CD → AWS";

		// Start Spring Boot
		SpringApplication.run(ActivityServiceApplication.class, args);


		// Display Service Info Box
		System.out.println();
		System.out.println(PURPLE + "╔════════════════════════════════════════════════════════════════════════════════╗" + RESET);
		System.out.println(PURPLE + "║" + CYAN + "                     🏋️‍♂️  FITNESS ACTIVITY SERVICE  🚴‍♀️      " + PURPLE + "║" + RESET);
		System.out.println(PURPLE + "╠════════════════════════════════════════════════════════════════════════════════╣" + RESET);
		System.out.println(GREEN + "🟢 Service Name: " + RESET + serviceName);

		// Dynamic profile color
		String profileColor = switch (activeProfile.toLowerCase()) {
			case "prod" -> RED;
			case "dev" -> YELLOW;
			default -> BLUE;
		};
		System.out.println(GREEN + "🟢 Profile     : " + profileColor + activeProfile + RESET);

		System.out.println(GREEN + "📌 Server Port : " + RESET + serverPort);
		System.out.println(GREEN + "🗄️ MongoDB URI : " + RESET + dbURI);
		System.out.println(GREEN + "⚙️ Tech Stack  : " + RESET + techStack);
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
	}
}
