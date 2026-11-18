package com.campaigniq.exception;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, Object>> handleValidationExceptions(
			MethodArgumentNotValidException ex){
		
		Map<String, String> errors = new LinkedHashMap<>();
		
		ex.getBindingResult().getFieldErrors().forEach(error -> 
		
		errors.put(error.getField(),error.getDefaultMessage())
		);
		
		Map<String, Object> response = new LinkedHashMap<>();
		
		response.put("status", HttpStatus.BAD_REQUEST.value());
		response.put("message", "Validation failed");
		response.put("errors", errors);
		
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		
	}
	
	
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<Map<String, Object>> handleInvalidRequestBody(
	        HttpMessageNotReadableException ex) {

	    Map<String, String> errors = new LinkedHashMap<>();

	    errors.put(
	        "request",
	        "Invalid JSON value. Check the field types and allowed campaign status values: ACTIVE, PAUSED, COMPLETED, CANCELLED"
	    );

	    Map<String, Object> response = new LinkedHashMap<>();

	    response.put("status", HttpStatus.BAD_REQUEST.value());
	    response.put("message", "Invalid request body");
	    response.put("errors", errors);

	    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}
	
	
	
}
