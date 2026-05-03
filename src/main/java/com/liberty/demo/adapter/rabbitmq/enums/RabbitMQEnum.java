package com.liberty.demo.adapter.rabbitmq.enums;

import lombok.Getter;

@Getter
public enum RabbitMQEnum {

    MAIN_QUEUE_NAME("might_stream"),
    SECOND_QUEUE_NAME("might_stream-2"),
    EXCHANGE_NAME("amq.topic");

    private final String name;

    RabbitMQEnum(String name) {
        this.name = name;
    }

}
