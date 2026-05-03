package com.liberty.demo.adapter.rabbitmq;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import reactor.rabbitmq.*;

@Configuration
public class RabbitSenderConfig {

    @Bean
    public Mono<Connection> connectionSenderMono() {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.useNio();
        Mono<Connection> mono = Mono.fromCallable(() -> connectionFactory.newConnection("reactor-rabbit")).cache();
        return mono;
    }

    @Bean
    public SenderOptions senderOptions(Mono<Connection> connectionSenderMono) {
        return new SenderOptions()
                .connectionMono(connectionSenderMono)
                .resourceManagementScheduler(Schedulers.boundedElastic());
    }

    @Bean
    public Sender sender(SenderOptions senderOptions) {
        return RabbitFlux.createSender(senderOptions);
    }
}