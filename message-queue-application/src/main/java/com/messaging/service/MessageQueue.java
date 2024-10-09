package com.messaging.service;

import com.messaging.model.Message;

import org.springframework.stereotype.Service;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Service class that manages the message queue.
 * This class is thread-safe and can be used concurrently by producers and consumers.
 */
@Service
public class MessageQueue {
    private final BlockingQueue<Message> queue = new LinkedBlockingQueue<>(100);

    /**
     * Adds a message to the queue. If the queue is full, this method will block until space becomes available.
     *
     * @param message The message to be added to the queue
     * @throws InterruptedException If the thread is interrupted while waiting
     */
    public void enqueue(Message message) throws InterruptedException {
        queue.put(message);
    }

    /**
     * Retrieves and removes a message from the queue. If the queue is empty, this method will block until a message becomes available.
     *
     * @return The message retrieved from the queue
     * @throws InterruptedException If the thread is interrupted while waiting
     */
    public Message dequeue() throws InterruptedException {
        return queue.take();
    }

    /**
     * Checks if the queue is empty.
     *
     * @return true if the queue is empty, false otherwise
     */
    public boolean isEmpty() {
        return queue.isEmpty();
    }

    /**
     * Returns the current size of the queue.
     *
     * @return The number of messages currently in the queue
     */
    public int size() {
        return queue.size();
    }
}