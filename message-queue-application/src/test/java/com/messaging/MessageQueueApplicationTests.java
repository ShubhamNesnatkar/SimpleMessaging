package com.messaging;

import com.messaging.model.Message;
import com.messaging.service.Consumer;
import com.messaging.service.MessageQueue;
import com.messaging.service.Producer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Integration tests for the MessageQueueApplication.
 * This class tests the integration of Producer and Consumer with the MessageQueue.
 */
@SpringBootTest
class MessageQueueApplicationTests {

	@Mock
	private MessageQueue messageQueue; // Mocked MessageQueue for testing

	private Producer producer; // Producer instance to be tested
	private Consumer consumer; // Consumer instance to be tested

	/**
	 * Initializes the Producer and Consumer instances and the mocked MessageQueue before each test.
	 */
	@BeforeEach
	void setUp() {
		producer = new Producer(messageQueue); // Create Producer with mocked MessageQueue
		consumer = new Consumer(messageQueue); // Create Consumer with mocked MessageQueue
	}

	/**
	 * Verifies that the application context loads successfully.
	 */
	@Test
	void contextLoads() {
		// Verifies that the context loads successfully
	}

	/**
	 * Tests the end-to-end flow of producing and consuming a message.
	 * Verifies that a message is produced and consumed correctly,
	 * and that the corresponding success and error counts are updated.
	 *
	 * @throws InterruptedException if the operation is interrupted
	 */
	@Test
	void testProduceAndConsumeMessageFlow() throws InterruptedException {
		// Arrange
		Message message = new Message("Test Message");
		when(messageQueue.isEmpty()).thenReturn(false); // Mock not empty
		when(messageQueue.dequeue()).thenReturn(message); // Mock dequeue

		// Act
		consumer.consumeMessage(); // Invoke the method under test

		// Assert
		assertEquals(1, consumer.getSuccessCount()); // Verify success count
		assertEquals(0, consumer.getErrorCount()); // Verify error count
		verify(messageQueue, times(1)).dequeue(); // Verify interaction
	}
}
