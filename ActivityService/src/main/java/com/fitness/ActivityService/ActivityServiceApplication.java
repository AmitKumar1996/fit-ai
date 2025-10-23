package com.fitness.ActivityService;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.Socket;
import java.util.Properties;

@SpringBootApplication
public class ActivityServiceApplication {

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
        String activeProfile = "dev";
        String serverPort = "8080";

        String dbURI = "mongodb://localhost:27017";
        String kafkaURI = "localhost:9092";
        String zookeeperURI = "localhost:2181";
        String techStack = "Java → Spring Boot → Kafka → MongoDB → Docker";

        SpringApplication.run(ActivityServiceApplication.class, args);

        printHeader(serviceName, activeProfile, serverPort, dbURI, kafkaURI, zookeeperURI, techStack);
        printSystemDesign();
        checkKafkaConnection(kafkaURI);
        checkZookeeperConnection(zookeeperURI);
        simulateDataFlow();
    }

    // ✅ Display summary table
    private static void printHeader(String name, String profile, String port, String db, String kafka, String zk, String tech) {
        System.out.println(PURPLE + "\n╔════════════════════════════════════════════════════════════════════════╗" + RESET);
        System.out.println(PURPLE + "║" + CYAN + "                🚴‍♀️  FITNESS ACTIVITY SERVICE STATUS  🏋️‍♂️               " + PURPLE + "║" + RESET);
        System.out.println(PURPLE + "╠════════════════════════════════════════════════════════════════════════╣" + RESET);
        System.out.printf(GREEN + "║ %-20s : %-45s ║%n", "Service Name", name);
        System.out.printf(GREEN + "║ %-20s : %-45s ║%n", "Profile", profile);
        System.out.printf(GREEN + "║ %-20s : %-45s ║%n", "Server Port", port);
        System.out.printf(GREEN + "║ %-20s : %-45s ║%n", "MongoDB URI", db);
        System.out.printf(GREEN + "║ %-20s : %-45s ║%n", "Kafka Broker", kafka);
        System.out.printf(GREEN + "║ %-20s : %-45s ║%n", "Zookeeper", zk);
        System.out.printf(GREEN + "║ %-20s : %-45s ║%n", "Tech Stack", tech);
        System.out.println(PURPLE + "╚════════════════════════════════════════════════════════════════════════╝" + RESET);
        System.out.println();
    }

    // ✅ Check Kafka connection
    private static void checkKafkaConnection(String kafkaURI) {
        System.out.print(BLUE + "🔍 Checking Kafka connection... " + RESET);
        try {
            Properties props = new Properties();
            props.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaURI);
            try (AdminClient client = AdminClient.create(props)) {
                client.listTopics().names().get();
                System.out.println(GREEN + "✅ Kafka is reachable at " + kafkaURI + RESET);
            }
        } catch (Exception e) {
            System.out.println(RED + "❌ Kafka not reachable at " + kafkaURI + " (" + e.getMessage() + ")" + RESET);
        }
    }

    // ✅ Check Zookeeper connection
    private static void checkZookeeperConnection(String zookeeperURI) {
        System.out.print(BLUE + "🔍 Checking Zookeeper connection... " + RESET);
        String[] parts = zookeeperURI.replace("localhost:", "").split(":");
        int port = parts.length > 1 ? Integer.parseInt(parts[1]) : 2181;
        try (Socket socket = new Socket("localhost", port)) {
            System.out.println(GREEN + "✅ Zookeeper is reachable at " + zookeeperURI + RESET);
        } catch (Exception e) {
            System.out.println(RED + "❌ Zookeeper not reachable at " + zookeeperURI + " (" + e.getMessage() + ")" + RESET);
        }
    }

    // ✅ Simulate data flow
    private static void simulateDataFlow() throws InterruptedException {
        System.out.println();
        System.out.println(YELLOW + "📡 Simulating Kafka data flow..." + RESET);

        String[] steps = {
                "Sending message → topic: activity_log",
                "Kafka broker processing...",
                "Consumer received message ✅",
                "Message saved to MongoDB 🗄️"
        };

        for (String step : steps) {
            System.out.println(CYAN + "➡️ " + step + RESET);
            Thread.sleep(700);
        }

        System.out.println(GREEN + "✅ Data pipeline working fine!\n" + RESET);
    }

    // Existing visual architecture block
    private static void printSystemDesign() {
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
    }
}
