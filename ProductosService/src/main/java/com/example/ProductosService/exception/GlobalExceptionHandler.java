package com.example.ProductosService.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;



@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(IllegalArgumentException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ErrorResponse handleValidationException(Exception ex) {
	    StringBuilder errorMessage = new StringBuilder();
	   
	    errorMessage.append(ex.getMessage());
	
	    
	    return new ErrorResponse(errorMessage.toString(), HttpStatus.BAD_REQUEST);
	}
	

	   @ExceptionHandler(HttpMessageNotReadableException.class)
	    @ResponseStatus(HttpStatus.BAD_REQUEST)
	    @ResponseBody
	    public ResponseEntity<ErrorResponse> handleHttpMessageNotReadable(HttpMessageNotReadableException ex) {
	        String errorMessage = "La unidad de medida no existe";
	        return ResponseEntity.status(HttpStatus.CREATED).body(new ErrorResponse(errorMessage, HttpStatus.BAD_REQUEST));
	        
	    }
	   
	   @ExceptionHandler(ResourceNotFoundException.class)
	   @ResponseStatus(HttpStatus.NOT_FOUND)
	   @ResponseBody
	   public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException ex) {
		   String errorMessage = "El producto no existe";
	        return ResponseEntity.status(HttpStatus.CREATED).body(new ErrorResponse(errorMessage, HttpStatus.NOT_FOUND));
	   }
}
