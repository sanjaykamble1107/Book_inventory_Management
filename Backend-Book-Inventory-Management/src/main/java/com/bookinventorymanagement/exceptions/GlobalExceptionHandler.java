package com.bookinventorymanagement.exceptions;

import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(AuthorNotFoundException.class)
	public ResponseEntity<ExceptionResponse> handler(AuthorNotFoundException ex) {
		ExceptionResponse exception = new ExceptionResponse(ex.getMessage(), LocalDateTime.now(),
				HttpStatus.NOT_FOUND.value());
		ResponseEntity<ExceptionResponse> response = new ResponseEntity<ExceptionResponse>(exception,
				HttpStatus.NOT_FOUND);
		return response;
	}

	@ExceptionHandler(BookNotFoundException.class)
	public ResponseEntity<ExceptionResponse> handler(BookNotFoundException ex) {
		ExceptionResponse exception = new ExceptionResponse(ex.getMessage(), LocalDateTime.now(),
				HttpStatus.NOT_FOUND.value());
		ResponseEntity<ExceptionResponse> response = new ResponseEntity<ExceptionResponse>(exception,
				HttpStatus.NOT_FOUND);
		return response;
	}

	@ExceptionHandler(BookConditionNotFoundException.class)
	public ResponseEntity<ExceptionResponse> handler(BookConditionNotFoundException ex) {
		ExceptionResponse exception = new ExceptionResponse(ex.getMessage(), LocalDateTime.now(),
				HttpStatus.NOT_FOUND.value());
		ResponseEntity<ExceptionResponse> response = new ResponseEntity<ExceptionResponse>(exception,
				HttpStatus.NOT_FOUND);
		return response;
	}

	@ExceptionHandler(BookReviewNotFoundException.class)
	public ResponseEntity<ExceptionResponse> handler(BookReviewNotFoundException ex) {
		ExceptionResponse exception = new ExceptionResponse(ex.getMessage(), LocalDateTime.now(),
				HttpStatus.NOT_FOUND.value());
		ResponseEntity<ExceptionResponse> response = new ResponseEntity<ExceptionResponse>(exception,
				HttpStatus.NOT_FOUND);
		return response;
	}

	@ExceptionHandler(CategoryNotFoundException.class)
	public ResponseEntity<ExceptionResponse> handler(CategoryNotFoundException ex) {
		ExceptionResponse exception = new ExceptionResponse(ex.getMessage(), LocalDateTime.now(),
				HttpStatus.NOT_FOUND.value());
		ResponseEntity<ExceptionResponse> response = new ResponseEntity<ExceptionResponse>(exception,
				HttpStatus.NOT_FOUND);
		return response;
	}

	@ExceptionHandler(InventoryNotFoundException.class)
	public ResponseEntity<ExceptionResponse> handler(InventoryNotFoundException ex) {
		ExceptionResponse exception = new ExceptionResponse(ex.getMessage(), LocalDateTime.now(),
				HttpStatus.NOT_FOUND.value());
		ResponseEntity<ExceptionResponse> response = new ResponseEntity<ExceptionResponse>(exception,
				HttpStatus.NOT_FOUND);
		return response;
	}

	@ExceptionHandler(PermRoleNotFoundException.class)
	public ResponseEntity<ExceptionResponse> handler(PermRoleNotFoundException ex) {
		ExceptionResponse exception = new ExceptionResponse(ex.getMessage(), LocalDateTime.now(),
				HttpStatus.NOT_FOUND.value());
		ResponseEntity<ExceptionResponse> response = new ResponseEntity<ExceptionResponse>(exception,
				HttpStatus.NOT_FOUND);
		return response;
	}

	@ExceptionHandler(PublisherNotFoundException.class)
	public ResponseEntity<ExceptionResponse> handler(PublisherNotFoundException ex) {
		ExceptionResponse exception = new ExceptionResponse(ex.getMessage(), LocalDateTime.now(),
				HttpStatus.NOT_FOUND.value());
		ResponseEntity<ExceptionResponse> response = new ResponseEntity<ExceptionResponse>(exception,
				HttpStatus.NOT_FOUND);
		return response;
	}

	@ExceptionHandler(PurchaseLogNotFoundException.class)
	public ResponseEntity<ExceptionResponse> handler(PurchaseLogNotFoundException ex) {
		ExceptionResponse exception = new ExceptionResponse(ex.getMessage(), LocalDateTime.now(),
				HttpStatus.NOT_FOUND.value());
		ResponseEntity<ExceptionResponse> response = new ResponseEntity<ExceptionResponse>(exception,
				HttpStatus.NOT_FOUND);
		return response;
	}

	@ExceptionHandler(ReviewerNotFoundException.class)
	public ResponseEntity<ExceptionResponse> handler(ReviewerNotFoundException ex) {
		ExceptionResponse exception = new ExceptionResponse(ex.getMessage(), LocalDateTime.now(),
				HttpStatus.NOT_FOUND.value());
		ResponseEntity<ExceptionResponse> response = new ResponseEntity<ExceptionResponse>(exception,
				HttpStatus.NOT_FOUND);
		return response;
	}

//	@ExceptionHandler(ShoppingCartNotFoundException.class)
//	public ResponseEntity<ExceptionResponse> handler(ShoppingCartNotFoundException ex) {
//		ExceptionResponse exception = new ExceptionResponse(ex.getMessage(), LocalDateTime.now(),
//				HttpStatus.NOT_FOUND.value());
//		ResponseEntity<ExceptionResponse> response = new ResponseEntity<ExceptionResponse>(exception,
//				HttpStatus.NOT_FOUND);
//		return response;
//	}

	@ExceptionHandler(StateNotFoundException.class)
	public ResponseEntity<ExceptionResponse> handler(StateNotFoundException ex) {
		ExceptionResponse exception = new ExceptionResponse(ex.getMessage(), LocalDateTime.now(),
				HttpStatus.NOT_FOUND.value());
		ResponseEntity<ExceptionResponse> response = new ResponseEntity<ExceptionResponse>(exception,
				HttpStatus.NOT_FOUND);
		return response;
	}

	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ExceptionResponse> handler(UserNotFoundException ex) {
		ExceptionResponse exception = new ExceptionResponse(ex.getMessage(), LocalDateTime.now(),
				HttpStatus.NOT_FOUND.value());
		ResponseEntity<ExceptionResponse> response = new ResponseEntity<ExceptionResponse>(exception,
				HttpStatus.NOT_FOUND);
		return response;
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<?> handleConstraintViolationExc(ConstraintViolationException ex, WebRequest request) {
		ExceptionResponse errRes = new ExceptionResponse(ex.getMessage(), LocalDateTime.now(),
				HttpStatus.NOT_FOUND.value());
		System.out.println("it is not detected");
		return new ResponseEntity<>(errRes, HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {

		List<String> validationErrs = new ArrayList<>();

		for (FieldError err : ex.getBindingResult().getFieldErrors()) {
			System.out.println("----" + err.getDefaultMessage());
			validationErrs.add(err.getDefaultMessage());
		}

		ErrorResponse errResp = new ErrorResponse("Validation Failed", validationErrs.toString());
		return new ResponseEntity<Object>(errResp, status);

	}

	@ExceptionHandler(BadCredentialsException.class)
	public ResponseEntity<ExceptionResponse> handler(BadCredentialsException ex) {
		ExceptionResponse exception = new ExceptionResponse(ex.getMessage(), LocalDateTime.now(),
				HttpStatus.NOT_FOUND.value());
		ResponseEntity<ExceptionResponse> response = new ResponseEntity<ExceptionResponse>(exception,
				HttpStatus.NOT_FOUND);
		return response;
	}
	
	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<ExceptionResponse> handler(NoSuchElementException ex) {
		ExceptionResponse exception = new ExceptionResponse(ex.getMessage(), LocalDateTime.now(),
				HttpStatus.NOT_FOUND.value());
		ResponseEntity<ExceptionResponse> response = new ResponseEntity<ExceptionResponse>(exception,
				HttpStatus.NOT_FOUND);
		return response;
	}
	
	
	@ExceptionHandler(InvalidInputException.class)
	public ResponseEntity<ExceptionResponse> handler(InvalidInputException ex) {
		ExceptionResponse exception = new ExceptionResponse(ex.getMessage(), LocalDateTime.now(),
				HttpStatus.NOT_FOUND.value());
		ResponseEntity<ExceptionResponse> response = new ResponseEntity<ExceptionResponse>(exception,
				HttpStatus.NOT_FOUND);
		return response;
	}
	
	
	@ExceptionHandler(SQLIntegrityConstraintViolationException.class)
	public ResponseEntity<ExceptionResponse> handler(SQLIntegrityConstraintViolationException ex) {
		ExceptionResponse exception = new ExceptionResponse(ex.getMessage(), LocalDateTime.now(),
				HttpStatus.NOT_FOUND.value());
		ResponseEntity<ExceptionResponse> response = new ResponseEntity<ExceptionResponse>(exception,
				HttpStatus.NOT_FOUND);
		return response;
	}
	

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ExceptionResponse> handler(Exception ex) {
		ExceptionResponse exception = new ExceptionResponse(ex.getMessage(), LocalDateTime.now(),
				HttpStatus.NOT_FOUND.value());
		ResponseEntity<ExceptionResponse> response = new ResponseEntity<ExceptionResponse>(exception,
				HttpStatus.NOT_FOUND);
		return response;
	}
}
