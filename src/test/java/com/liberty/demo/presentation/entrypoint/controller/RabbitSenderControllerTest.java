package com.liberty.demo.presentation.entrypoint.controller;

import com.liberty.demo.presentation.entrypoint.request.RedisRequest;
import com.liberty.demo.service.component.RedisContext;
import com.liberty.demo.service.enums.RedisOperationType;
import com.liberty.demo.service.rabbitmq.RabbitMQProducer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class RabbitSenderControllerTest {

    @InjectMocks
    private RabbitSenderController controller;

    @Mock
    private RabbitMQProducer producer;

    @Mock
    private RabbitTemplate rabbitTemplate;

    @Mock
    private RedisContext redisContext;

    @Mock
    private Queue queue;

    @Autowired
    private MockMvc mockMvc;

    private final String endpoint = "/api/v1/notifications";
    private final String restService = "/send";
    private final String queueName = "queueName-1";
    private final String message = "Teste";

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        producer = mock(RabbitMQProducer.class);
        queue = new Queue(queueName, Boolean.TRUE);
    }

    @Test
    public void testSendMock() throws InterruptedException {

        ResponseEntity<String> responseMock = new ResponseEntity<>(message, HttpStatus.CREATED);
       //Mockito.doReturn(responseMock).when(controller).send(message);
        Mockito.doNothing().when(producer).sendMessage(message);

        producer.sendMessage(message);
        ResponseEntity<String> response = controller.send(message);

        //Mockito.verify(controller, Mockito.times(1)).send(message);
        Mockito.verify(producer, Mockito.times(1)).sendMessage(message);

        assertEquals(responseMock.getStatusCode(), response.getStatusCode());
        assertEquals(responseMock.getBody(), response.getBody());
    }

    @Test
    void testSend_WhenExceptionThrown_ShouldThrowInterruptedException() {
        String message = "Hello world!";
        when(redisContext.executeStrategyType(any(RedisRequest.class), eq(RedisOperationType.SEND)))
                .thenThrow(new RuntimeException("Erro simulado"));

        InterruptedException exception = assertThrows(InterruptedException.class, () -> {
            controller.send(message);
        });

        assertTrue(exception.getMessage().contains("Processo interronpido!"));
        verify(producer, never()).sendMessage(anyString());
    }

    @Test
    void testSend_WhenExceptionAfterSendMessage_ShouldThrowInterruptedException_AndSendMessageCalled() {
        String message = "Mensagem de teste";

        // Configura o RedisContext para retornar algo válido
        when(redisContext.executeStrategyType(any(), eq(RedisOperationType.SEND)))
                .thenReturn("OK");

        // Configura o producer para lançar exceção após o envio
        doAnswer(invocation -> {
            throw new RuntimeException("Erro após envio da mensagem");
        }).when(producer).sendMessage(message);

        InterruptedException ex = assertThrows(InterruptedException.class, () -> {
            controller.send(message);
        });

        assertTrue(ex.getMessage().contains("Processo interronpido!"));
        verify(producer, times(1)).sendMessage(message);
    }

    @Test
    void testSend_ShouldSendMessageEvenIfGetFails() throws InterruptedException {
        String message = "mensagem crítica";

        // Primeira operação (SEND) com sucesso
        when(redisContext.executeStrategyType(any(RedisRequest.class), eq(RedisOperationType.SEND)))
                .thenReturn("ok")
                .thenThrow(new RuntimeException("Falha na operação GET")); // Segunda chamada falha

        // Simula envio da mensagem sem erro
        doNothing().when(producer).sendMessage("ok");

        // Verifica que mesmo com falha posterior, a mensagem é enviada
        InterruptedException ex = assertThrows(InterruptedException.class, () -> {
            controller.send(message);
        });

        assertTrue(ex.getMessage().contains("Processo interronpido!"));
        verify(producer, times(1)).sendMessage("ok");
    }



    @AfterEach
    public void tearDown() {
    }
}