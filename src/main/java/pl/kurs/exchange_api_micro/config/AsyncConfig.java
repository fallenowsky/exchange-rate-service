package pl.kurs.exchange_api_micro.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import pl.kurs.exchange_api_micro.properties.AsyncProperties;

@Configuration
public class AsyncConfig {


    @Bean
    public ThreadPoolTaskExecutor asyncExecutor(AsyncProperties asyncProperties) {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setMaxPoolSize(asyncProperties.getThreads());
        executor.initialize();
        return executor;
    }
}
