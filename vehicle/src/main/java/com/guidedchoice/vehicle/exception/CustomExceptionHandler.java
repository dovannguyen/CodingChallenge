package com.guidedchoice.vehicle.exception;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.extern.slf4j.Slf4j;

/**
 * This class is used to intercept any exception we want to custom handle.
 * As of this writing we are handling all bean Validation errors caught
 * in the VehicleController while attempting to deserialize the input Json.
 * 
 * It also handles the not found record.
 * 
 * @author Dovan Nguyen
 *
 */
@Slf4j
@SuppressWarnings({"unchecked","rawtypes"})
@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

	private static final String VALIDATION_ERROR = "Validation Failed";
	private static final String NOT_FOUND_ERROR = "Record Not Found";
	
	/**
	 * A MethodArgumentNotValidException is thrown when a bean Validation error occurred.
	 * We are overriding this method to fetch the default message(s) of each error.
	 */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> details = new ArrayList<>();
        
        // the default error message is buried in the BindingResult of the exception 
        ex.getBindingResult().getAllErrors().stream().forEach(error -> {
        	details.add(error.getDefaultMessage());
        });
        
    	logger.info(VALIDATION_ERROR + ": " + details);

    	String path = getRequestUri(request);
    	ErrorResponse errorResponse = new ErrorResponse(Instant.now(), HttpStatus.BAD_REQUEST.value(), VALIDATION_ERROR, details, path);
        
        return new ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(RecordNotFoundException.class)
    public final ResponseEntity<Object> handleUserNotFoundException(RecordNotFoundException ex, WebRequest request) {
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        
    	logger.info(NOT_FOUND_ERROR + ": " + details);

        String path = getRequestUri(request);
        ErrorResponse error = new ErrorResponse(Instant.now(), HttpStatus.NOT_FOUND.value(), NOT_FOUND_ERROR, details, path);
        return new ResponseEntity(error, HttpStatus.NOT_FOUND);
    }

    /**
     * The request.getDescription returns the string "uri={path}"
     * We need to split that string into 2 parts in order to get the {path} only.
     * 
     * @param request
     * @return the request uri path as a string
     */
    private String getRequestUri(WebRequest request) {
    	String[] path = request.getDescription(false).split("=");
    	return path[1];
    }
 }
