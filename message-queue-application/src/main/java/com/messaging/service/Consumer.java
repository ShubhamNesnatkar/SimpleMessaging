package com.messaging.service;

import com.messaging.model.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Service class responsible for consuming messages from the queue.
 */
@Service
public class Consumer {
    private static final Logger logger = LoggerFactory.getLogger(Consumer.class);
    private final MessageQueue messageQueue;
    private final AtomicInteger successCount = new AtomicInteger(0);
    private final AtomicInteger errorCount = new AtomicInteger(0);

    /**
     * Constructs a new Consumer with the given MessageQueue.
     *
     * @param messageQueue The MessageQueue from which messages will be consumed
     */
    public Consumer(MessageQueue messageQueue) {
        this.messageQueue = messageQueue;
    }

    /**
     * Consumes a message from the queue if one is available.
     * If an InterruptedException occurs, it logs the error, increments the error count, and re-interrupts the thread.
     *
     * @return The consumed message as a string, or null if no message was consumed
     */
    public String consumeMessage() {
        try {
            if (!messageQueue.isEmpty()) {
                Message message = messageQueue.dequeue();
                processMessage(message);
                successCount.incrementAndGet();
                logger.info("Consumed: {}", message);
                return message.content();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            errorCount.incrementAndGet();
            logger.error("Error consuming message", e);
        }
        return null;
    }

    /**
     * Simulates processing of a message. In a real application, this method would contain the logic for handling the message.
     *
     * @param message The message to be processed
     */
    private void processMessage(Message message) {
        // Simulate processing time
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * @return The number of successfully processed messages
     */
    public int getSuccessCount() {
        return successCount.get();
    }

    /**
     * @return The number of errors encountered while consuming messages
     */
    public int getErrorCount() {
        return errorCount.get();
    }
}
