package com.merchant.management.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
public class AsyncConfig {

	
	   @Bean
	    public ThreadPoolTaskExecutor taskExecutor() {
	        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
	        executor.setCorePoolSize(5);  // Minimum number of threads
	        executor.setMaxPoolSize(10);  // Maximum number of threads
	        executor.setQueueCapacity(25);  // Number of tasks that can be queued
	        executor.setThreadNamePrefix("Async-Executor-");
	        executor.initialize();
	        return executor;
	    }
}
