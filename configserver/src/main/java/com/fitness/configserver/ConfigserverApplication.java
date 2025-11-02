package com.fitness.configserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

@SpringBootApplication
@EnableConfigServer
public class ConfigserverApplication {

	// ANSI color codes

	public static final String RESET = "\u001B[0m";


	public static final String GREEN = "\u001B[32m";
	public static final String YELLOW = "\u001B[33m";
	public static final String RED = "\u001B[31m";

	public static final String BLUE = "\u001B[34m";
	public static final String CYAN = "\u001B[36m";

	public static final String PURPLE = "\u001B[35m";

	public static void main(String[] args) throws InterruptedException {

		ConfigurableApplicationContext context = SpringApplication.run(ConfigserverApplication.class, args);
		Environment env = context.getEnvironment();

		String serviceName = "Fitness Config Server";
		String activeProfile = env.getActiveProfiles().length > 0 ? env.getActiveProfiles()[0] : "default";
		String serverPort = env.getProperty("server.port", "8090");

		// Microservices and their ports
		String[][] services = {
				{"Eureka Server", "8761"},
				{"API Gateway", "8092"},
				{"User Service", "8084"},
				{"AI Service", "8086"},
				{"Activity Service", "8085"},
				{"Config Server", serverPort}
		};

		String profileColor = activeProfile.equals("prod") ? RED :
				activeProfile.equals("dev") ? YELLOW : BLUE;

		int totalWidth = 80;

		System.out.println();
		System.out.println(PURPLE + "╔" + "═".repeat(totalWidth - 2) + "╗" + RESET);
		String title = "⚙️  FITNESS CONFIG SERVER  ⚙️";
		int padding = (totalWidth - 2 - title.length()) / 2;
		System.out.println(PURPLE + "║" + " ".repeat(padding) + CYAN + title + PURPLE +
				" ".repeat(totalWidth - 2 - title.length() - padding) + "║" + RESET);
		System.out.println(PURPLE + "╠" + "═".repeat(totalWidth - 2) + "╣" + RESET);

		System.out.println(formatRow("Service Name", serviceName, YELLOW, totalWidth));
		System.out.println(formatRow("Active Profile", activeProfile, profileColor, totalWidth));
		System.out.println(formatRow("Server Port", serverPort, GREEN, totalWidth));

		System.out.println(PURPLE + "╠" + "═".repeat(totalWidth - 2) + "╣" + RESET);
		System.out.println(formatRow("Microservices Overview", "Registered Ports", CYAN, totalWidth));

		for (String[] s : services) {
			System.out.println(formatRow("→ " + s[0], s[1], GREEN, totalWidth));
		}

		System.out.println(PURPLE + "╚" + "═".repeat(totalWidth - 2) + "╝" + RESET);
		System.out.println();

		printSystemDesign();

		// Boot animation
		System.out.println(CYAN + "🚀 Starting " + serviceName + "..." + RESET);
		int totalSteps = 20;
		for (int i = 1; i <= totalSteps; i++) {
			int progress = i * 100 / totalSteps;
			String bar = "▓".repeat(i) + "░".repeat(totalSteps - i);
			System.out.print("\r" + GREEN + "[" + bar + "] " + progress + "%" + RESET);
			Thread.sleep(100);
		}

		System.out.println("\n" + GREEN + "✅ Boot complete! " + CYAN + serviceName + " is now serving configs!" + RESET);
		System.out.println();
	}

	private static String formatRow(String label, String value, String valueColor, int totalWidth) {
		int contentWidth = totalWidth - 4;
		String content = String.format("%-20s : %s", label, valueColor + value + RESET);
		int realLength = content.replaceAll("\u001B\\[[;\\d]*m", "").length();
		return PURPLE + "║ " + content + " ".repeat(Math.max(0, contentWidth - realLength)) + " ║" + RESET;

	}


	private static void printSystemDesign() {
		System.out.println();
		System.out.println(PURPLE + "╔════════════════════════════════════════════════════════╗" + RESET);
		System.out.println(PURPLE + "║" + CYAN + "             FITNESS MICROSERVICES TOPOLOGY            " + PURPLE + "║" + RESET);
		System.out.println(PURPLE + "╠════════════════════════════════════════════════════════╣" + RESET);
		System.out.println(GREEN + "  🧑‍💻 Client" + RESET + " → " + YELLOW + "🌐 API Gateway (8092)" + RESET);
		System.out.println(YELLOW + "                |" + RESET);
		System.out.println(YELLOW + "         -----------------------------" + RESET);
		System.out.println(YELLOW + "         |         |          |       |" + RESET);
		System.out.println(GREEN + "     👤 User   🤖 AI   🏃 Activity   ⚙️ Config" + RESET);
		System.out.println("         |         |          |       |");
		System.out.println(GREEN + "   🗄️ PostgreSQL  🔁 Redis  ☁️ Kafka  AWS S3" + RESET);
		System.out.println(PURPLE + "╚════════════════════════════════════════════════════════╝" + RESET);
		System.out.println();
	}
}
