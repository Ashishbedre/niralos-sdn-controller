package com.other.app.NiralosFiveGCore.BackendServices.MultiThreadingConfig;

import java.util.concurrent.Executor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
@EnableAsync
@Configuration
@EnableScheduling
public class SchedulerConfig {

		@Bean
	    public ThreadPoolTaskScheduler taskScheduler() {
	        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
	        scheduler.setPoolSize(20);  // Configure the pool size based on your requirements
	        scheduler.setThreadNamePrefix("ScheduledTask-");
	        scheduler.initialize();
	        return scheduler;
	    }
		// Bean for async task executor
	    @Bean(name = "taskExecutor")
	    public Executor taskExecutor() {
	        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
	        executor.setCorePoolSize(10); // Core pool size
	        executor.setMaxPoolSize(20); // Maximum pool size
	        executor.setQueueCapacity(50); // Queue capacity
	        executor.setThreadNamePrefix("AsyncThread-"); // Thread name prefix
	        executor.initialize();
	        return executor;
	    }

}
