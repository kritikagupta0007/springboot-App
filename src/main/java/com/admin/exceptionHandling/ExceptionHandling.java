package com.admin.exceptionHandling;

import java.util.HashMap;
import java.util.Map;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class ExceptionHandling {

	@ExceptionHandler(InputException.class)
	public ResponseEntity<String> handleInputExcepion(InputException inputException) {
		inputException.setMessage(inputException.getMessage());
		return new ResponseEntity<String>("Please check the input fields", new HttpHeaders(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleMethodArgsNotValidException(MethodArgumentNotValidException ex) {
		Map<String, String> resp = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String message = error.getDefaultMessage();
			resp.put(fieldName, message);
		});
		return new ResponseEntity<Map<String, String>>(resp, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(HttpMediaTypeNotSupportedException.class)
	public ResponseEntity<String> handleHttpMediaTypeNotSupportedException(
			HttpMediaTypeNotSupportedException httpMediaTypeNotSupportedException) {
		return new ResponseEntity<String>("Please check the input media type", HttpStatus.UNSUPPORTED_MEDIA_TYPE);
	}

	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<String> handleHttpRequestMethodNotSupportedException(
			HttpRequestMethodNotSupportedException httpRequestMethodNotSupportedException) {
		return new ResponseEntity<String>("Please choose the correct method type", new HttpHeaders(),
				HttpStatus.METHOD_NOT_ALLOWED);
	}

	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<String> handleMethodArgumentTypeMismatchException(
			MethodArgumentTypeMismatchException httpRequestMethodNotSupportedException) {
		return new ResponseEntity<String>("Please choose the correct method type", new HttpHeaders(),
				HttpStatus.METHOD_NOT_ALLOWED);
	}
	
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<String> handleHttpMessageNotReadableException(
			HttpMessageNotReadableException httpRequestMethodNotSupportedException) {
		return new ResponseEntity<String>("The provided inputs are not readable", new HttpHeaders(),
				HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<String> handleConstraintViolationException(
			ConstraintViolationException httpRequestMethodNotSupportedException) {
		return new ResponseEntity<String>("Provided parameters are not valid ", new HttpHeaders(),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
