package com.messaging.runner;

import com.messaging.service.Consumer;
import com.messaging.service.Producer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * A demonstration class that simulates the message queue system.
 * It manually triggers the production and consumption of messages during application startup.
 * Implements the CommandLineRunner interface to run after the Spring Boot application starts.
 */
@Component
public class MessageQueueDemo implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(MessageQueueDemo.class);
    private final Producer producer;
    private final Consumer consumer;

    /**
     * Constructor to inject the producer and consumer services.
     *
     * @param producer The producer service responsible for producing messages
     * @param consumer The consumer service responsible for consuming messages
     */
    public MessageQueueDemo(Producer producer, Consumer consumer) {
        this.producer = producer;
        this.consumer = consumer;
    }

    /**
     * The run method is executed when the application starts. It demonstrates
     * the interaction between the producer and consumer in the message queue.
     *
     * @param args Command-line arguments passed during application startup
     * @throws Exception if any error occurs during message production or consumption
     */
    @Override
    public void run(String... args) throws Exception {
        logger.info("Starting Message Queue Demonstration");

        // Run the demonstration for 30 seconds
        long endTime = System.currentTimeMillis() + 30000;

        while (System.currentTimeMillis() < endTime) {
            // Manually trigger produce and consume operations
            producer.produceMessage();
            consumer.consumeMessage();

            // Log current statistics
            logger.info("Messages processed successfully: {}", consumer.getSuccessCount());
            logger.info("Errors encountered: {}", consumer.getErrorCount());

            // Wait for a short time before the next iteration
            Thread.sleep(2000);
        }

        logger.info("Message Queue Demonstration completed");
        logger.info("Final statistics:");
        logger.info("Total messages processed successfully: {}", consumer.getSuccessCount());
        logger.info("Total errors encountered: {}", consumer.getErrorCount());
    }
}
