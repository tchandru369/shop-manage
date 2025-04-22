package com.merchant.management.security;

import java.util.concurrent.Executor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync
public class AsyncConfig implements AsyncConfigurer{

	
	   @Bean(name = "taskExecutor")
	    public Executor taskExecutor() {
	        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
	        executor.setCorePoolSize(10);  // Minimum number of threads
	        executor.setMaxPoolSize(20);  // Maximum number of threads
	        executor.setQueueCapacity(100);  // Number of tasks that can be queued
	        executor.setThreadNamePrefix("Async-Executor-");
	        executor.initialize();
	        return executor;
	    }
	   
	   
	   public Executor getTaskExecutor() {
		   return taskExecutor();
	   }
}
