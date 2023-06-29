package com.example.ProductosService.service;

public interface EventPublisherService {

	public void publishEvent(String message);
	
	public void publishEventAsync(String message);
}
