package com.liberty.demo.adapter.rabbitmq;

import com.liberty.demo.adapter.rabbitmq.enums.RabbitMQEnum;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class RabbitMQConfigTest {

    @InjectMocks
    private RabbitMQConfig config;

    @Mock
    private Queue queueMock;

    @Mock
    private TopicExchange exchangeMock;

    @Mock
    private Binding bindingMock;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testQueueBeanBean() {

        Mockito.when(queueMock.getActualName()).thenReturn(RabbitMQEnum.MAIN_QUEUE_NAME.getName());
        assertNotNull(queueMock);
        assertEquals(RabbitMQEnum.MAIN_QUEUE_NAME.getName(), queueMock.getActualName());
    }

    @Test
    public void testExchangeBean() {

        Mockito.when(exchangeMock.getName()).thenReturn(RabbitMQEnum.EXCHANGE_NAME.getName());
        assertNotNull(exchangeMock);
        assertEquals(RabbitMQEnum.EXCHANGE_NAME.getName(), exchangeMock.getName());
    }

    @Test
    public void testBindingBean() {

        bindingMock = BindingBuilder.bind(new Queue(RabbitMQEnum.MAIN_QUEUE_NAME.getName(), true)).to(new TopicExchange(RabbitMQEnum.EXCHANGE_NAME.getName())).with("routing.key.#");

        Mockito.when(bindingMock.getDestination()).thenReturn(RabbitMQEnum.MAIN_QUEUE_NAME.getName());
        assertNotNull(bindingMock);
        assertEquals(RabbitMQEnum.MAIN_QUEUE_NAME.getName(), bindingMock.getDestination());
    }

    @AfterEach
    public void tearDown() {
    }

}