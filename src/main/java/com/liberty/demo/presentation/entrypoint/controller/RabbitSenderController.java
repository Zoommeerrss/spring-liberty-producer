package com.liberty.demo.presentation.entrypoint.controller;

import com.liberty.demo.presentation.entrypoint.request.RedisRequest;
import com.liberty.demo.service.component.RedisContext;
import com.liberty.demo.service.enums.RedisOperationType;
import com.liberty.demo.service.rabbitmq.RabbitMQProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/notifications")
public class RabbitSenderController {

    // slf4j logger
    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitSenderController.class);

    @Autowired
    private RabbitMQProducer producer;

    @Autowired
    private RedisContext redisContext;

    @PostMapping("/send")
    public ResponseEntity<String> send(@RequestBody String message) throws InterruptedException {

        String response = null;
        try {
            response = redisContext.executeStrategyType(
                    RedisRequest.builder()
                            .key(UUID.randomUUID().toString())
                            .value(message)
                            .build(),
                    RedisOperationType.SEND);
            producer.sendMessage(response);
            response = redisContext.executeStrategyType(
                    RedisRequest.builder()
                            .key(UUID.randomUUID().toString())
                            .value(message)
                            .build(),
                    RedisOperationType.GET);
        }
        catch (Exception e) {
            throw new InterruptedException("Processo interronpido!\n " +  e.getMessage());
        }
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

}
