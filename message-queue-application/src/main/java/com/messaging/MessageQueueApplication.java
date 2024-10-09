package com.messaging;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * Main application class for the Message Queue system.
 * This class bootstraps the Spring Boot application and enables scheduling for periodic tasks.
 */
@SpringBootApplication
@EnableAsync
public class MessageQueueApplication {

	/**
	 * The main method, used to run the application.
	 *
	 * @param args Command line arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(MessageQueueApplication.class, args);
	}
}
