package com.other.app.NiralosFiveGCore.BackendServices.MultiThreadingConfig;

import java.util.concurrent.Executor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	private static final Logger loggerTypeB = LoggerFactory.getLogger("5G-FRONTEND:SchedulerConfig");

		@Bean
	    public ThreadPoolTaskScheduler taskScheduler() {
			// Create an instance of ThreadPoolTaskScheduler
	        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
	        scheduler.setPoolSize(20);  // Configure the pool size based on your requirements
	        scheduler.setThreadNamePrefix("ScheduledTask-");
	        scheduler.initialize();
			loggerTypeB.info("taskScheduler: Task scheduler initialized with pool size and thread name prefix");
	        return scheduler;
	    }
		// Bean for async task executor
	    @Bean(name = "taskExecutor")
	    public Executor taskExecutor() {
			// Create an instance of ThreadPoolTaskExecutor
	        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
	        executor.setThreadNamePrefix("AsyncThread-"); // Thread name prefix
	        executor.setCorePoolSize(20);  // Minimum number of threads (always available)
	        executor.setMaxPoolSize(25);   // Maximum number of threads
	        executor.setQueueCapacity(50); // Queue size for pending tasks if all threads are busy
	        executor.setKeepAliveSeconds(50);
	        executor.initialize();
			loggerTypeB.info("taskExecutor: Task executor initialized with core pool size max pool size queue capacity and thread name prefix");

			return executor;
	    }

}
