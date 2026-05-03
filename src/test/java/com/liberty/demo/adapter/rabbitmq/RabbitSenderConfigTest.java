package com.liberty.demo.adapter.rabbitmq;

import com.rabbitmq.client.Connection;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Mono;
import com.rabbitmq.client.ConnectionFactory;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import reactor.test.StepVerifier;

public class RabbitSenderConfigTest {

    @Mock
    private RabbitSenderConfig config;

    private final String connectionName = "reactor-rabbit";

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        config = new RabbitSenderConfig();
    }

    @Test
    public void testConnectionSenderMono() {

        Mono<Connection> mono = config.connectionSenderMono();
        assertNotNull(mono);
    }

    @Test
    public void testRabbitClientConnectionFactory() {

        ConnectionFactory connectionFactory = mock(ConnectionFactory.class);

        StepVerifier.create(Mono.fromCallable(() -> connectionFactory.newConnection(connectionName)).cache())
                .expectNext()
                .expectComplete()
                .verify();
    }

    @AfterEach
    public void tearDown() {
    }

}