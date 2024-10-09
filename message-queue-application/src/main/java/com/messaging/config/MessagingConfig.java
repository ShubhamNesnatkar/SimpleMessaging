package com.messaging.config;

import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import java.util.concurrent.Executor;

/**
 * Configuration class for application-wide beans and settings.
 */
public class MessagingConfig {
    /**
     * Creates and configures a ThreadPoolTaskExecutor for managing concurrent tasks.
     *
     * @return Configured Executor instance
     */
    @Bean
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(2);
        executor.setMaxPoolSize(2);
        executor.setQueueCapacity(500);
        executor.setThreadNamePrefix("MessageQueue-");
        executor.initialize();
        return executor;
    }
}
