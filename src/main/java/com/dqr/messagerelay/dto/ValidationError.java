package com.dqr.messagerelay.dto;

import java.util.Objects;

/**
 * This DTO class encapsulates an error message generated from failed validation of a DTO field. It is intended to be sent back with a 400 BAD REQUEST response.
 * <ul>
 * <li>fieldName - the name of the DTO field that failed validation</li>
 * <li>message - a message describing to the user why validation failed</li>
 * </ul>
 * @author ericm
 *
 */

public class ValidationError {
	
	private String fieldName;
	private String message;
	
	public ValidationError(String fieldName, String message) {
		this.fieldName = fieldName;
		this.message = message;
	}
	
	public String getFieldName() {
		return fieldName;
	}
	public String getMessage() {
		return message;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(fieldName, message);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj instanceof ValidationError) {
			ValidationError other = (ValidationError) obj;
			return 	Objects.equals(this.fieldName, other.fieldName) && 
					Objects.equals(this.message, other.message);
		} else return false;
	}
}
