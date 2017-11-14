package com.dqr.messagerelay.validation;

import com.dqr.messagerelay.dto.ValidationError;
import com.dqr.messagerelay.exceptions.ValidationException;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

/**
 * {@code Validator} performs validation checks against a data transfer object's fields.
 * <P>
 * <strong>How to use:</strong>
 * <ol>
 * 	<li>Create a new instance of {@code Validator}.</li>
 * 	<li>Call validation methods:
 * 		<ul>
 * 		<li>{@code required(...)} -- Validation check for fields that cannot be empty</li>
 * 		<li>{@code requiredIf(...)} -- Validation check for fields required under certain conditions to not be empty</li>
 * 		<li>{@code validate(...)} -- Validation check for fields whose contents must meet certain conditions, 
 * 		e.g. a date field that must fall within a certain date range</li>
 * 		</ul>
 * 	</li>
 * 	<li>Finish by calling {@code finishValidation()}. If errors were found, a {@link ValidationException} 
 * 	will be thrown, to be handled by {@link ValidationExceptionHandler}.</li>
 * </ol>
 * <P>
 * Validation methods have {@code fieldName} and {@code message} parameters. {@code fieldName} is the client-side or UI-side
 * name of the field being validated. {@code message} is the error message to be shown in the client or UI after failed validation.
 * @see ValidationError
 * @see ValidationException
 * @see ValidationExceptionHandler
 * 
 * @author ericm
 *
 */
public class Validator {
	
	private Set<ValidationError> errors = new HashSet<>();;
	
	/**
	 * Checks whether the given field is present (not null).
	 * @param field the object field to validate
	 * @param fieldName the client-side name of this field
	 * @param message the error message to return to the client for a failed validation check
	 * @return {@code Validator} instance for method chaining
	 */
	public Validator required(Object field, String fieldName, String message) {
		boolean isEmpty = (field == null) ||
				(field instanceof String && ((String) field).isEmpty());
		
		if (isEmpty) {
			errors.add(new ValidationError(fieldName, message));
		}
		return this;
	}
	
	/**
	 * Checks whether the given field is present (not null), but only applies this check if the given conditions are met.
	 * @param t the object to perform the conditional check against
	 * @param condition the condition that must be satisfied before the "is required" check will be performed
	 * @param field the object field to validate
	 * @param fieldName the client-side name of this field
	 * @param message the error message to return to the client for a failed validation check
	 * @return {@code Validator} instance for method chaining
	 */
	public <T> Validator requiredIf(T t, Predicate<T> condition, Object field, String fieldName, String message) {
		if (t != null && condition.test(t)) {
			return required(field, fieldName, message);
		}
		return this;
	}
	
	/**
	 * Checks whether the given field meets the provided conditions.
	 * @param t the object to perform the validation check against
	 * @param condition the validation check to perform. Validation passes if the predicate returns true. 
	 * @param fieldName the client-side name of the field associated with this validation check
	 * @param message the error message to return to the client for a failed validation check
	 * @return {@code Validator} instance for method chaining
	 */
	public <T> Validator validate(T t, Predicate<T> condition, String fieldName, String message) {
		if (t != null && ! condition.test(t)) {
			errors.add(new ValidationError(fieldName, message));
		}
		return this;
	}
	
	public void finish() {
		if (errors.size() > 0) {
			throw new ValidationException(errors);
		}
	}
}
