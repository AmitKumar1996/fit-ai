package com.futness.userService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class UserServiceApplication {

	// ANSI color codes
	public static final String RESET = "\u001B[0m";
	public static final String GREEN = "\u001B[32m";
	public static final String YELLOW = "\u001B[33m";
	public static final String RED = "\u001B[31m";
	public static final String BLUE = "\u001B[34m";
	public static final String CYAN = "\u001B[36m";
	public static final String PURPLE = "\u001B[35m";

	public static void main(String[] args) throws InterruptedException {
		ConfigurableApplicationContext context = SpringApplication.run(UserServiceApplication.class, args);
		Environment env = context.getEnvironment();

		// 🔹 Dynamic values from application.yml
		String serviceName = env.getProperty("spring.application.name", "Unknown Service");
		String activeProfile = env.getActiveProfiles().length > 0 ? env.getActiveProfiles()[0] : "default";
		String serverPort = env.getProperty("server.port", "8080");
		String dbUrl = env.getProperty("spring.datasource.url", "Not Configured");
		String techStack = env.getProperty("app.tech-stack", "Not Defined");

		// Profile color logic
		String profileColor = activeProfile.equals("prod") ? RED :
				activeProfile.equals("dev") ? YELLOW : BLUE;

		int totalWidth = 80;

		// Print service info table
		System.out.println();
		System.out.println(PURPLE + "╔" + "═".repeat(totalWidth - 2) + "╗" + RESET);
		String title = "🏋️‍♂️  " + serviceName.toUpperCase() + "  🚴‍♀️";
		int padding = (totalWidth - 2 - title.length()) / 2;
		System.out.println(PURPLE + "║" + " ".repeat(padding) + CYAN + title +
				PURPLE + " ".repeat(totalWidth - 2 - title.length() - padding) + "║" + RESET);
		System.out.println(PURPLE + "╠" + "═".repeat(totalWidth - 2) + "╣" + RESET);

		System.out.println(formatRow("Service Name", serviceName, YELLOW, totalWidth));
		System.out.println(formatRow("Profile", activeProfile, profileColor, totalWidth));
		System.out.println(formatRow("Server Port", serverPort, GREEN, totalWidth));
		System.out.println(formatRow("Database URL", dbUrl, GREEN, totalWidth));
		System.out.println(formatRow("Tech Stack", techStack, GREEN, totalWidth));

		System.out.println(PURPLE + "╚" + "═".repeat(totalWidth - 2) + "╝" + RESET);
		System.out.println();

		// Print system design diagram
		printSystemDesign();

		// Animated Boot Progress
		System.out.println(CYAN + "🎉 Booting " + serviceName + "..." + RESET);
		int totalSteps = 20;
		for (int i = 1; i <= totalSteps; i++) {
			int progress = i * 100 / totalSteps;
			String bar = "▓".repeat(i) + "░".repeat(totalSteps - i);
			System.out.print("\r" + GREEN + "[" + bar + "] " + progress + "%" + RESET);
			Thread.sleep(100);
		}

		System.out.println("\n" + GREEN + "✅ Boot complete! " + CYAN + serviceName + " is up and running!" + RESET);
	}

	private static String formatRow(String label, String value, String valueColor, int totalWidth) {
		int contentWidth = totalWidth - 4; // 2 for borders, 2 for spaces
		String content = String.format("%-15s : %s", label, valueColor + value + RESET);
		int realLength = content.replaceAll("\u001B\\[[;\\d]*m", "").length();
		return PURPLE + "║ " + content + " ".repeat(Math.max(0, contentWidth - realLength)) + " ║" + RESET;
	}

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
}
