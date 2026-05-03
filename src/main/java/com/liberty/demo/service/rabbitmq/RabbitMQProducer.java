package com.liberty.demo.service.rabbitmq;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private Queue queue;

//    @Autowired
//    public RabbitMQProducer (RabbitTemplate rabbitTemplate, Queue queue) {
//        this.rabbitTemplate = rabbitTemplate;
//        this.queue = queue;
//    }

    public void sendMessage(String message) {
        rabbitTemplate.convertAndSend(queue.getName(), message);
    }
}