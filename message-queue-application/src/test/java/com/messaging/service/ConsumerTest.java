package com.messaging.service;

import com.messaging.model.Message;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for the Consumer class.
 * This class tests the functionality of the Consumer, which is responsible for consuming messages
 * from the MessageQueue.
 */
class ConsumerTest {

    @Mock
    private MessageQueue messageQueue; // Mocked MessageQueue for testing

    private Consumer consumer; // Consumer instance to be tested

    /**
     * Initializes the Consumer instance and the mocked MessageQueue before each test.
     */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Initialize mocks
        consumer = new Consumer(messageQueue); // Create Consumer with mocked MessageQueue
    }

    /**
     * Tests the successful consumption of a message.
     * Verifies that the success count is incremented and the error count remains zero.
     *
     * @throws InterruptedException if the operation is interrupted
     */
    @Test
    void testConsumeMessageSuccess() throws InterruptedException {
        when(messageQueue.isEmpty()).thenReturn(false); // Mock not empty
        when(messageQueue.dequeue()).thenReturn(new Message("Test Message")); // Mock dequeue
        consumer.consumeMessage(); // Invoke the method under test
        assertEquals(1, consumer.getSuccessCount()); // Verify success count
        assertEquals(0, consumer.getErrorCount()); // Verify error count
    }

    /**
     * Tests the handling of an exception during message consumption.
     * Verifies that the success count remains zero and the error count is incremented.
     *
     * @throws InterruptedException if the operation is interrupted
     */
    @Test
    void testConsumeMessageFailure() throws InterruptedException {
        when(messageQueue.isEmpty()).thenReturn(false); // Mock not empty
        when(messageQueue.dequeue()).thenThrow(new InterruptedException()); // Mock exception on dequeue
        consumer.consumeMessage(); // Invoke the method under test
        assertEquals(0, consumer.getSuccessCount()); // Verify success count
        assertEquals(1, consumer.getErrorCount()); // Verify error count
    }
}
