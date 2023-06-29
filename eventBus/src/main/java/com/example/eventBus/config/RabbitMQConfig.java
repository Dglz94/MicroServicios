package com.example.eventBus.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.AsyncRabbitTemplate;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
	

    private String exchangeName = "eventProductExchange";
    private String routingKey = "products";
    private String queueName = "eventos";

    @Bean
    public Queue eventQueue() {
        return new Queue(queueName, true);
    }

    @Bean
    public DirectExchange eventExchange() {
        return new DirectExchange(exchangeName, true, false);
    }
    
    @Bean
    public Binding eventBinding() {
        return BindingBuilder.bind(eventQueue())
                .to(eventExchange())
                .with(routingKey);
    }
    
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setRoutingKey(routingKey);
        rabbitTemplate.setExchange(exchangeName);
        return rabbitTemplate;
    }
    
    @Bean
    public AsyncRabbitTemplate asyncRabbitTemplate(RabbitTemplate rabbitTemplate) {
        return new AsyncRabbitTemplate(rabbitTemplate);
    }

    
    
    
}