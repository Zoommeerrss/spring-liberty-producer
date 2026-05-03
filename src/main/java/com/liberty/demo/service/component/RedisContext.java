package com.liberty.demo.service.component;

import com.liberty.demo.presentation.entrypoint.request.RedisRequest;
import com.liberty.demo.service.RedisStrategyService;
import com.liberty.demo.service.enums.RedisOperationType;
import com.liberty.demo.service.exception.RedisOperationNotFound;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Objects;

@AllArgsConstructor
@Component
public class RedisContext {

    /**
     * use the bean created in the RedisStrategyConfig class
     * the strategies list will be registered when the app starts
     */
    private final Map<RedisOperationType, RedisStrategyService> executeOperationType;

    // execute the strategy type according the declared type and the expected function
    public String executeStrategyType(RedisRequest request, RedisOperationType redisOperationType) throws RedisOperationNotFound {
        RedisStrategyService notificationStrategy = executeOperationType.getOrDefault(redisOperationType, null);
        if (Objects.isNull(notificationStrategy)) {
            throw new RedisOperationNotFound("Redis Operation Type not found. Type: " + redisOperationType);
        }
        return notificationStrategy.execute(request);
    }
}
