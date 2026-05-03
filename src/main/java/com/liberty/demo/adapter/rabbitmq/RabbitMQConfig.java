package com.liberty.demo.adapter.rabbitmq;

import com.liberty.demo.adapter.rabbitmq.enums.RabbitMQEnum;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Bean
    public Queue queue() {
        return new Queue(RabbitMQEnum.MAIN_QUEUE_NAME.getName(), true);
    }

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(RabbitMQEnum.EXCHANGE_NAME.getName());
    }

    @Bean
    public Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("routing.key.#");
    }
}
