package com.example.ProductosService.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ProductosService.exception.SuccessResponse;
import com.example.ProductosService.model.Producto;
import com.example.ProductosService.service.EventPublisherService;
import com.example.ProductosService.service.ProductoService;
import com.google.gson.JsonObject;



@RestController
@RequestMapping("/api/productos")
public class ProductoController {

	private final ProductoService productoService;
	private final EventPublisherService eventPublisherService;
	
	@Autowired
	public ProductoController(ProductoService productoService, EventPublisherService eventPublisherService) {
		super();
		this.productoService = productoService;
		this.eventPublisherService = eventPublisherService;
	}
	
	@PostMapping()
	public ResponseEntity<?> saveProducto(@Valid @RequestBody Producto producto, BindingResult bindingResult){
	
		if (bindingResult.hasErrors()) {
			 List<FieldError> fieldErrors = bindingResult.getFieldErrors();
		        StringBuilder errorMessage = new StringBuilder();
		        
		        for (FieldError fieldError : fieldErrors) {
		            errorMessage.append(fieldError.getField()).append(": ").append(fieldError.getDefaultMessage()).append("\n");
		        }
		        
		        throw new IllegalArgumentException(errorMessage.toString());
        }
		
		
		
		 Producto savedProducto = productoService.saveProducto(producto);
		 
		 String eventoJson = crearEventoJson(producto, producto.getId(), "create");
			eventPublisherService.publishEventAsync(eventoJson);
		 return ResponseEntity.status(HttpStatus.CREATED).body(new SuccessResponse("Producto Creado", savedProducto));
		
	}
	
	@GetMapping()
	public ResponseEntity<?> getAllProductos(){
		 return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse("Lista de productos", productoService.getAllProductos()));
		//return productoService.getAllProductos();
	}
	
	@PutMapping("{id}")
	public ResponseEntity<?> updateProducto(@Valid @RequestBody Producto producto, BindingResult bindingResult, @PathVariable("id") String id) {
	
		String eventoJson = crearEventoJson(producto, id, "update");
		
		Producto editedProducto = productoService.updateProducto(producto,id);
		eventPublisherService.publishEventAsync(eventoJson);
		return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse("Producto Editado", editedProducto ));
	
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<?> deleteProducto(@PathVariable("id") String id){
		
		 Producto deletedProducto = productoService.deleteProducto(id);
		 String eventoJson = crearEventoJson(deletedProducto, id, "deleted");
		 eventPublisherService.publishEventAsync(eventoJson);
		 
		 return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse("Producto Eliminado", deletedProducto));
	
	}
	

	 private String crearEventoJson(Producto producto, String id, String operacion) {
	     
	        JsonObject evento = new JsonObject();
	        evento.addProperty("operacion", operacion);
	        evento.addProperty("idProducto", id);
	        evento.addProperty("nombre", producto.getNombre());
	        evento.addProperty("precio", producto.getPrecio());
	        evento.addProperty("unidadMedida", producto.getUnidadMedida().toString());
	        evento.addProperty("calve", producto.getClave());


	        return evento.toString();
	    }
}	