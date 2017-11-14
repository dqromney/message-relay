package com.dqr.messagerelay.exceptions;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.dqr.messagerelay.dto.ValidationError;

import java.util.HashSet;
import java.util.Set;

public class ValidationException extends RuntimeException {

	private static final long serialVersionUID = -8202084309494621051L;
	private Set<ValidationError> errors;
	
	public ValidationException() {
		errors = new HashSet<>();
	}
	
	public ValidationException(Set<ValidationError> errors) {
		this.errors = errors;
	}
	
	public ValidationException(Throwable cause) {
		super(cause);
	}
	
	public ValidationException(Set<ValidationError> errors, Throwable cause) {
		this(cause);
		this.errors = errors;
	}
	
	/**
	 * This method is overridden to return the list of validation errors, written as a JSON string.
	 */
	@Override
	public String getMessage() {
		
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			return mapper.writeValueAsString(errors);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			throw new RuntimeException("Error in converting ValidationError set to JSON format.",e);
		}
	}
	
	public Set<ValidationError> getErrors() {
		return errors;
	}
}
