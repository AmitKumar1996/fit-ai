package com.fitness.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

import java.util.HashMap;
import java.util.TreeMap;

@SpringBootApplication
public class GatewayApplication {
	// ANSI color codes
	public static final String RESET = "\u001B[0m";
	public static final String GREEN = "\u001B[32m";
	public static final String YELLOW = "\u001B[33m";
	public static final String RED = "\u001B[31m";
	public static final String BLUE = "\u001B[34m";
	public static final String CYAN = "\u001B[36m";
	public static final String PURPLE = "\u001B[35m";
	public static void main(String[] args) throws InterruptedException {
		ConfigurableApplicationContext context = SpringApplication.run(GatewayApplication.class, args);
		Environment env = context.getEnvironment();
		// Dynamic values
		String serviceName = "API Gateway Service";
		String activeProfile = env.getActiveProfiles().length > 0 ? env.getActiveProfiles()[0] : "default";
		String serverPort = env.getProperty("server.port", "8092");
		String techStack = "Spring Cloud Gateway → Java → Spring Boot → Netflix OSS → CI/CD → AWS";
		// Microservices and their ports
		String[][] services = {
				{"Eureka Server", "8761"},
				{"API Gateway", serverPort},
				{"User Service", "8084"},
				{"AI Service", "8086"},
				{"Activity Service", "8085"},
				{"Config Server", "8090"}
		};
		String profileColor = activeProfile.equals("prod") ? RED :
				activeProfile.equals("dev") ? YELLOW : BLUE;

		int totalWidth = 80;

		// Print header
		System.out.println();
		System.out.println(PURPLE + "╔" + "═".repeat(totalWidth - 2) + "╗" + RESET);
		String title = "🌐 API GATEWAY SERVICE 🚦";
		int padding = (totalWidth - 2 - title.length()) / 2;
		System.out.println(PURPLE + "║" + " ".repeat(padding) + CYAN + title + PURPLE +
				" ".repeat(totalWidth - 2 - title.length() - padding) + "║" + RESET);
		System.out.println(PURPLE + "╠" + "═".repeat(totalWidth - 2) + "╣" + RESET);

		System.out.println(formatRow("Service Name", serviceName, YELLOW, totalWidth));
		System.out.println(formatRow("Active Profile", activeProfile, profileColor, totalWidth));
		System.out.println(formatRow("Server Port", serverPort, GREEN, totalWidth));
		System.out.println(formatRow("Tech Stack", techStack, GREEN, totalWidth));

		System.out.println(PURPLE + "╠" + "═".repeat(totalWidth - 2) + "╣" + RESET);
		System.out.println(formatRow("Microservices Overview", "Registered Ports", CYAN, totalWidth));

		for (String[] s : services) {
			System.out.println(formatRow("→ " + s[0], s[1], GREEN, totalWidth));
		}

		System.out.println(PURPLE + "╚" + "═".repeat(totalWidth - 2) + "╝" + RESET);
		System.out.println();

		printSystemDesign();

		// Boot animation
		System.out.println(CYAN + "🚀 Booting " + serviceName + "..." + RESET);
		int totalSteps = 20;
		for (int i = 1; i <= totalSteps; i++) {
			int progress = i * 100 / totalSteps;
			String bar = "▓".repeat(i) + "░".repeat(totalSteps - i);
			System.out.print("\r" + GREEN + "[" + bar + "] " + progress + "%" + RESET);
			Thread.sleep(100);
		}

		System.out.println("\n" + GREEN + "✅ Boot complete! " + CYAN + serviceName + " is up and routing traffic!" + RESET);
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
		System.out.println(PURPLE + "║" + CYAN + "             MICROSERVICES TOPOLOGY OVERVIEW            " + PURPLE + "║" + RESET);
		System.out.println(PURPLE + "╠════════════════════════════════════════════════════════╣" + RESET);
		System.out.println(GREEN + "  🧑‍💻 Client" + RESET + " → " + YELLOW + "🌐 API Gateway (8092)" + RESET);
		System.out.println(YELLOW + "                |" + RESET);
		System.out.println(YELLOW + "        -----------------------------" + RESET);
		System.out.println(YELLOW + "        |         |          |       |" + RESET);
		System.out.println(GREEN + "    👤 User   🤖 AI   🏃 Activity   ⚙️ Config" + RESET);
		System.out.println("        |         |          |       |");
		System.out.println(GREEN + "  🗄️ PostgreSQL  🔁 Redis  ☁️ Kafka  AWS S3" + RESET);
		System.out.println(PURPLE + "╚════════════════════════════════════════════════════════╝" + RESET);
		System.out.println();
	}
}




//
//int A={1, 2 ,3, 4, 5,6};
//
//
//o/p:-  3, 4, 6, 1, 2
//
//
//		int ptr=0;
//         int ptr2=A.length-1;
//for(int i=0;i<A.length;i++){
//
//	A[]
//
//		}

//while(ptr<ptr2){
//	int temp=A[ptr];
//	A[ptr]=A[ptr2];
//	A[ptr2]=temp;
//}
//
//1mg
//
//producer, consumer
//
//
//6 5 4 3 2 1
//
//HashMap<Integer, Integer> map=new HashMap<>();
//
//map.put(1,10);
//
//map.put(2,20);
//TreeMap<Integer, Integer> tree= new TreeMap<>();
//


//
//
//
//
//Transactions(ID(PK) , type, amount, date)
//
//select * from Transactions  as t where  date(DD-MM-YYYY-MM-HH)==??-??-????-??-?? > ??-??-????-??-??; (select count(type))
//
//
//
//classs Transactions{
//
//        }
//
//
//Map<String, Long >transactionMap=transactions.stream().collect(collectors.groupingby(i->i, collectors.count())).entrySet().collecters(Transactions :: getType);
//
//IT->6
//BPO->9
//
//
//
//
//
//{
//  CREDIT: 651.00,
//  DEBIT: 120.00,
//  REFUND: 170.50
//}
//
//public static void main(String[] args) {
//    List<Transaction> transactions = Arrays.asList(
//                new Transaction(1, 101, 250.75, "CREDIT", "SUCCESS",  1737165600000L), // 2025-01-18 10:00:00
//    new Transaction(2, 102, 120.00, "DEBIT",  "SUCCESS",  1737169200000L), // 2025-01-18 11:00:00
//    new Transaction(3, 101, 450.00, "CREDIT", "FAILED",   1737136800000L), // 2025-01-18 02:00:00
//    new Transaction(4, 103,  80.50, "REFUND", "SUCCESS",  1737176400000L), // 2025-01-18 13:00:00
//    new Transaction(5, 104, 600.00, "DEBIT",  "SUCCESS",  1737087600000L), // 2025-01-17 12:00:00 (day before)
//    new Transaction(6, 101, 100.25, "CREDIT", "SUCCESS",  1737165600000L), // 2025-01-18 10:00:00
//    new Transaction(7, 102, 220.00, "DEBIT",  "FAILED",   1737172800000L), // 2025-01-18 12:00:00
//    new Transaction(8, 103,  50.00, "CREDIT");
//
//    transactions.stream().collect(collecters.groupingby(i->i, collectors.count())),Transactions :: getDate  )
//    .entryset().collect(collectors.groupingby(i->i, Transactions:: getType)).sum(Transactions::getAmount);
//
//}
//
//
//
//@Data
//class Transactions{
//
// long id;
// double amount;
// String type;
// String status;
// Date date;
//
//}


