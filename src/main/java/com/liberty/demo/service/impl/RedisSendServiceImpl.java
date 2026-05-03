package com.liberty.demo.service.impl;

import com.liberty.demo.config.utils.RedisUtils;
import com.liberty.demo.presentation.entrypoint.request.RedisRequest;
import com.liberty.demo.service.RedisStrategyService;
import com.liberty.demo.service.enums.RedisOperationType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class RedisSendServiceImpl implements RedisStrategyService {

    @Autowired
    private RedisUtils<String> redisStringUtil;

    @Override
    public String execute(RedisRequest request) {
        try {
            redisStringUtil.set(request.getKey(), request.getValue());
            redisStringUtil.setExpire(request.getKey(), 2000, TimeUnit.MINUTES);
            return "success";
        }
        catch (Exception e) {
            return "error";
        }
    }

    // all strategies should have the operation type to identify what operation will be executed
    @Override
    public RedisOperationType operationType() {
        return RedisOperationType.SEND;
    }
}
