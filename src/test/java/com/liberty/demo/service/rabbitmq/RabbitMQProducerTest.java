package com.liberty.demo.service.rabbitmq;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RabbitMQProducerTest {

    @InjectMocks
    private RabbitMQProducer producer;

    @Mock
    private RabbitTemplate rabbitTemplate;

    @Mock
    private Queue queue;

    private final String queueName = "queueName-1";
    private final String message = "Teste";

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSendMessage() {

        when(queue.getName()).thenReturn(queueName);
        doNothing().when(rabbitTemplate).convertAndSend(queueName, message);
        producer.sendMessage(message);
        verify(queue, times(1)).getName();
        verify(rabbitTemplate, times(1)).convertAndSend(queue.getName(),message);
    }

    @AfterEach
    public void tearDown() {
    }
}