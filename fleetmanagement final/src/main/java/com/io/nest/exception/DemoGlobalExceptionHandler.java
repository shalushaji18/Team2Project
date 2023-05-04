package com.io.nest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class DemoGlobalExceptionHandler extends ResponseEntityExceptionHandler{
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<String> UserNotFoundHandler(Throwable throwable){
		return new ResponseEntity<String>(throwable.getMessage(), HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(DeviceNotFoundException.class)
	public ResponseEntity<String> DeviceNotFoundHandler(Throwable throwable){
		return new ResponseEntity<String>(throwable.getMessage(), HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(ClusterNotFoundException.class)
	public ResponseEntity<String> ClusterNotFoundHandler(Throwable throwable){
		return new ResponseEntity<String>(throwable.getMessage(), HttpStatus.NOT_FOUND);
	}
	

}
