package com.liberty.demo.config;

import com.liberty.demo.service.RedisStrategyService;
import com.liberty.demo.service.enums.RedisOperationType;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

@Configuration
@AllArgsConstructor
public class RedisStrategyConfig {

    // strategy list
    private final List<RedisStrategyService> operationStrategies;

    // the bean who will provide the strategies for the RedisContext class
    @Bean
    public Map<RedisOperationType, RedisStrategyService> executeOperationType() {
        Map<RedisOperationType, RedisStrategyService> operationType = new EnumMap<>(RedisOperationType.class);
        operationStrategies.forEach(strategy -> operationType.put(strategy.operationType(), strategy));
        return operationType;
    }
}
