package com.example.eventBus.components;


import org.springframework.amqp.rabbit.annotation.RabbitListener;


import org.springframework.stereotype.Component;

@Component
public class EventConsumer {
	

	@RabbitListener(queues="events")
	public void receiveEvent(String event) {
		System.out.println("Event received: " + event);

	}
	

}
