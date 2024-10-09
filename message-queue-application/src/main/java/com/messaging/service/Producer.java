package com.messaging.service;

import com.messaging.model.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Service class responsible for producing messages and adding them to the queue.
 */
@Service
public class Producer {
    private static final Logger logger = LoggerFactory.getLogger(Producer.class);
    private final MessageQueue messageQueue;
    private final AtomicInteger messageCounter = new AtomicInteger(0);

    /**
     * Constructs a new Producer with the given MessageQueue.
     *
     * @param messageQueue The MessageQueue to which produced messages will be added
     */
    public Producer(MessageQueue messageQueue) {
        this.messageQueue = messageQueue;
    }

    /**
     * Produces a new message and adds it to the queue.
     * If an InterruptedException occurs, it logs the error and re-interrupts the thread.
     */
    // we can also use cron expression to schedule the task
    @Scheduled(initialDelay = 30, fixedRate = 1000)
    public void produceMessage() {
        try {
            int messageNumber = messageCounter.incrementAndGet();
            Message message = new Message("Message " + messageNumber);
            messageQueue.enqueue(message);
            logger.info("Produced: {}", message);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            logger.error("Error producing message", e);
        }
    }
}
