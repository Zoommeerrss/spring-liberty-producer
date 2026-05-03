package com.liberty.demo.adapter.rabbitmq.enums;

import com.liberty.demo.presentation.entrypoint.request.PersonRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RabbitMQEnumTest {

    private final String main = "might_stream";
    private final String second = "might_stream-2";
    private final String exchange ="amq.topic";

    @BeforeEach
    public void setUp() {
    }

    @Test
    public void testAllEnum() {

        assertEquals(main, RabbitMQEnum.MAIN_QUEUE_NAME.getName());
        assertEquals(second, RabbitMQEnum.SECOND_QUEUE_NAME.getName());
        assertEquals(exchange, RabbitMQEnum.EXCHANGE_NAME.getName());
    }

    @Test
    public void testEnumConstructor() {

        RabbitMQEnum mainQueueName = RabbitMQEnum.MAIN_QUEUE_NAME;
        RabbitMQEnum secondQueueName = RabbitMQEnum.SECOND_QUEUE_NAME;
        RabbitMQEnum exchangeName = RabbitMQEnum.EXCHANGE_NAME;

        assertEquals(main, mainQueueName.getName());
        assertEquals(second, secondQueueName.getName());
        assertEquals(exchange, exchangeName.getName());
    }

    @AfterEach
    public void tearDown() {
    }
}