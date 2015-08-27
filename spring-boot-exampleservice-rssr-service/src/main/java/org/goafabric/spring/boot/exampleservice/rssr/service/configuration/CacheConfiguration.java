package org.goafabric.spring.boot.exampleservice.rssr.service.configuration;

import com.google.common.cache.CacheBuilder;
import org.goafabric.common.spring.service.resilience.ListCacheKeyGenerator;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.guava.GuavaCacheManager;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
@EnableCaching
public class CacheConfiguration extends CachingConfigurerSupport {
    private static final long CACHE_SIZE = 1000l;
    private static final long CACHE_EXPIRY = 60l;

    //names of the different caches, at least one per concern
    public static final String BUSINESS = "business";
    public static final String SECURITY = "security";

    //unless condition to prevent caching of failed requests / null results
    public static final String UNLESS = "#result.isExecuted() == false";

    @Bean
    @Override
    public CacheManager cacheManager() {
        final GuavaCacheManager guavaCacheManager =  new GuavaCacheManager(BUSINESS, SECURITY);
        guavaCacheManager.setCacheBuilder(CacheBuilder.newBuilder()
                .maximumSize(CACHE_SIZE).expireAfterAccess(CACHE_EXPIRY, TimeUnit.MINUTES));
        return guavaCacheManager;
    }

    @Bean
    @Override
    public KeyGenerator keyGenerator() {
        return new ListCacheKeyGenerator();
    }
}
