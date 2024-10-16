package com.other.app.config.CacheConfig;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.benmanes.caffeine.cache.Caffeine;

@Configuration
@EnableCaching
public class CacheConfig {
	 private static final Logger logger = LoggerFactory.getLogger(CacheConfig.class);
	 @Bean
	    public CacheManager cacheManager() {
	        // Create a Caffeine cache with the desired configuration
	        Caffeine<Object, Object> caffeineBuilder = Caffeine.newBuilder()
	            .expireAfterWrite(10, TimeUnit.SECONDS);  // Cache expires after 5 minutes

	        // Create a CaffeineCache instance for topologyData
	        CaffeineCache topologyDataCache = new CaffeineCache("topologyData", caffeineBuilder.build());

	        // Create a SimpleCacheManager and register the caches
	        SimpleCacheManager cacheManager = new SimpleCacheManager();
	        List<CaffeineCache> caches = Arrays.asList(topologyDataCache);  // Add your cache to the list
	        cacheManager.setCaches(caches);  // Set the caches in the cache manager

	        return cacheManager;  // Return the configured cache manager
	    }
}
