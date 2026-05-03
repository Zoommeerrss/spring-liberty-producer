package com.liberty.demo.service;

import com.liberty.demo.presentation.entrypoint.request.RedisRequest;
import com.liberty.demo.service.enums.RedisOperationType;

public interface RedisStrategyService {

    public String execute(RedisRequest request);

    public RedisOperationType operationType();
}
