package com.jsp.spring.pmsm.utility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.jsp.spring.pmsm.exception.ProductNotFoundByIdException;
import com.jsp.spring.pmsm.exception.ProductNotFoundException;

@RestControllerAdvice
public class ApplicationHandler extends ResponseEntityExceptionHandler {

	private ErrorStructure<String> errorStructure;
	private ErrorStructure<Object> structureList;


	public ApplicationHandler(ErrorStructure<String> errorStructure,ErrorStructure<Object> structureList) {
		super();
		this.errorStructure = errorStructure;
		this.structureList=structureList;
	}


	@ExceptionHandler(ProductNotFoundByIdException.class)
	public ResponseEntity<ErrorStructure<String>> productNotFoundByIdException(ProductNotFoundByIdException ex)
	{
		return ResponseEntity.ok(errorStructure.setErrorStatuscode(HttpStatus.NOT_FOUND.value())
				.setErrorMessage(ex.getMessage())
				.setErrorData("product Object With the Given id doesnot exist"));
	}


	@ExceptionHandler(ProductNotFoundException.class)
	public ResponseEntity<ErrorStructure<String>> productNotFoundException(ProductNotFoundException ex)
	{
		return ResponseEntity.ok(errorStructure.setErrorStatuscode(HttpStatus.NOT_FOUND.value())
				.setErrorMessage(ex.getMessage())
				.setErrorData("product doesnot exists"));
	}

	//	@Override
	//	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
	//			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
	//		List<ObjectError> errors = ex.getAllErrors();
	//		List<String> messages=new ArrayList<>();
	//		errors.forEach(error->{
	//			String message=error.getDefaultMessage();
	//			messages.add(message);
	//		});
	//		
	//		return ResponseEntity.badRequest().body(structureList.setErrorStatuscode(HttpStatus.BAD_REQUEST.value())
	//				.setErrorMessage("Invalid inputs")
	//				.setErrorData(messages));
	//	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
//		List<ObjectError> errors = ex.getAllErrors();
		Map<String, String> messages=new HashMap<>();
		ex.getAllErrors().forEach(error->{
//			FieldError fieldError=(FieldError)error;
//			messages.put(fieldError.getField(),fieldError.getDefaultMessage());
			messages.put(((FieldError)error).getField(),error.getDefaultMessage());
		});

		return ResponseEntity.badRequest().body(structureList.setErrorStatuscode(HttpStatus.BAD_REQUEST.value())
				.setErrorMessage("Invalid inputs")
				.setErrorData(messages));
	}
}
