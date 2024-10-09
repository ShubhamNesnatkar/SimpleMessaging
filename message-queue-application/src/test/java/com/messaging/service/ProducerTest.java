package com.messaging.service;

import com.messaging.model.Message;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Unit tests for the Producer class.
 * This class tests the functionality of the Producer, which is responsible for producing messages
 * and enqueuing them to the MessageQueue.
 */
class ProducerTest {

    @Mock
    private MessageQueue messageQueue; // Mocked MessageQueue for testing

    private Producer producer; // Producer instance to be tested

    /**
     * Initializes the Producer instance and the mocked MessageQueue before each test.
     */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Initialize mocks
        producer = new Producer(messageQueue); // Create Producer with mocked MessageQueue
    }

    /**
     * Tests the successful production of a message.
     * Verifies that the enqueue method of the MessageQueue is called exactly once.
     *
     * @throws InterruptedException if the operation is interrupted
     */
    @Test
    void testProduceMessageSuccess() throws InterruptedException {
        producer.produceMessage(); // Invoke the method under test
        verify(messageQueue, times(1)).enqueue(any(Message.class)); // Verify interaction
    }

    /**
     * Tests the handling of an exception during message production.
     * Verifies that the enqueue method is called once even if an exception occurs.
     *
     * @throws InterruptedException if the operation is interrupted
     */
    @Test
    void testProduceMessageFailure() throws InterruptedException {
        doThrow(new InterruptedException()).when(messageQueue).enqueue(any(Message.class)); // Mock failure
        producer.produceMessage(); // Invoke the method under test
        verify(messageQueue, times(1)).enqueue(any(Message.class)); // Verify interaction
    }
}
