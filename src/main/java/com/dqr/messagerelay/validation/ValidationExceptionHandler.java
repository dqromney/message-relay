package com.dqr.messagerelay.validation;

import com.dqr.messagerelay.dto.ValidationError;
import com.dqr.messagerelay.exceptions.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ValidationExceptionHandler {
	
	@Autowired
    private MessageSource messageSource;
	
	@ExceptionHandler(ValidationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public List<ValidationError> processValidationError(ValidationException exception) {
		Locale locale = LocaleContextHolder.getLocale();
		
		// Convert message codes to messages from message source, return list
		return exception.getErrors()
				.stream()
				.map(err -> {
					String messageText = messageSource.getMessage(err.getMessage(), null, locale);
					return new ValidationError(err.getFieldName(),messageText);
				})
				.collect(Collectors.toList());
	}
	
}
