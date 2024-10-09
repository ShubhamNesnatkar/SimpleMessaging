package com.messaging.model;

/**
 * Represents a message in the queue system.
 * This class is immutable to ensure thread-safety.
 */
public record Message(String content) {
    /**
     * Constructs a new Message with the given content.
     *
     * @param content The content of the message
     */
    public Message {
        if (content == null || content.isEmpty()) {
            throw new IllegalArgumentException("Message content cannot be null or empty");
        }
    }
}