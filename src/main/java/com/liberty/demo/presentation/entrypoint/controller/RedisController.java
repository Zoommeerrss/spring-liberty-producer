package com.liberty.demo.presentation.entrypoint.controller;

import com.liberty.demo.presentation.entrypoint.request.RedisRequest;
import com.liberty.demo.presentation.entrypoint.response.RedisResponse;
import com.liberty.demo.service.component.RedisContext;
import com.liberty.demo.service.enums.RedisOperationType;
import com.liberty.demo.service.impl.RedisGetServiceImpl;
import com.liberty.demo.service.impl.RedisSendServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
@RequestMapping("/api/v1/redis")
public class RedisController {

    @Autowired
    private RedisContext redisContext;

    @PostMapping("/add")
    public ResponseEntity<RedisResponse> addRedisKeyValue(@RequestBody RedisRequest redisRequest){

        String response = redisContext.executeStrategyType(redisRequest, RedisOperationType.SEND);

        return new ResponseEntity<RedisResponse>(
                RedisResponse.builder()
                        .httpStatus(HttpStatus.CREATED.value())
                        .message(response)
                .build(), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<RedisResponse> getFromCache(@RequestParam(value = "key") String key) {

        String response = redisContext.executeStrategyType(RedisRequest.builder().key(key).build(), RedisOperationType.GET);
        return new ResponseEntity<RedisResponse>(
                RedisResponse.builder()
                        .httpStatus(HttpStatus.OK.value())
                        .message(response)
                        .build(), HttpStatus.OK);
    }

}