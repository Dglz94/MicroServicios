package com.example.ProductosService.service.impl;

import org.springframework.amqp.rabbit.AsyncRabbitTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ProductosService.service.EventPublisherService;



@Service
public class EventPublisherServiceImpl implements EventPublisherService {

	private final RabbitTemplate rabbitTemplate;
	private final AsyncRabbitTemplate asyncRabbitTemplate;
	private final String exchangeName = "eventProductExchange";
	private final String routingKey = "products";

	
	@Autowired
	public EventPublisherServiceImpl(RabbitTemplate rabbitTemplate, AsyncRabbitTemplate asyncRabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
		this.asyncRabbitTemplate = asyncRabbitTemplate;
	}
	
	public void publishEvent(String message) {
		rabbitTemplate.convertAndSend(exchangeName, routingKey, message );
	}
	
	public void publishEventAsync(String message) {
		asyncRabbitTemplate.convertSendAndReceive(exchangeName, routingKey, message);
	}
	
}
