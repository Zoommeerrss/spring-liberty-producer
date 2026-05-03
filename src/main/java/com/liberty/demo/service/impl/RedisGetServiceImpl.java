package com.liberty.demo.service.impl;

import com.liberty.demo.config.utils.RedisUtils;
import com.liberty.demo.presentation.entrypoint.request.RedisRequest;
import com.liberty.demo.service.RedisStrategyService;
import com.liberty.demo.service.enums.RedisOperationType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RedisGetServiceImpl implements RedisStrategyService {

    @Autowired
    private RedisUtils<String> redisStringUtil;

    @Override
    public String execute(RedisRequest request) {
        return redisStringUtil.get(request.getKey());
    }

    // all strategies should have the operation type to identify what operation will be executed
    @Override
    public RedisOperationType operationType() {
        return RedisOperationType.GET;
    }
}
