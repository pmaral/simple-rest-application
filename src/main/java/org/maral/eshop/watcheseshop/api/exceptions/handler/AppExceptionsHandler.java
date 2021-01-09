package org.maral.eshop.watcheseshop.api.exceptions.handler;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.maral.eshop.watcheseshop.api.exceptions.EshopServiceException;
import org.maral.eshop.watcheseshop.api.ui.models.errors.ErrorMessage;
import org.maral.eshop.watcheseshop.api.ui.models.errors.validation.ValidationError;
import org.maral.eshop.watcheseshop.api.ui.models.errors.validation.ValidationErrorsResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 *
 */

@RestControllerAdvice
public class AppExceptionsHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {Exception.class, EshopServiceException.class})
    public ResponseEntity<Object> handleAnyException(Exception exception, WebRequest request) {
        return new ResponseEntity<>(
            new ErrorMessage(new Date(), getLocalizedMessageOrMesssageFromException(exception), ""),
            new HttpHeaders(),
            HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
        HttpHeaders headers, HttpStatus status, WebRequest request) {

        return new ResponseEntity<>(
            processValidationsErrors(ex.getBindingResult().getFieldErrors()),
            HttpStatus.BAD_REQUEST
        );
    }

    private String getLocalizedMessageOrMesssageFromException(Exception exception) {
        return Optional.ofNullable(exception.getLocalizedMessage())
            .orElseGet(exception::toString);
    }

    private ValidationErrorsResponse processValidationsErrors(List<FieldError> fieldFieldErrors) {
        return new ValidationErrorsResponse(
            new Date(),
            "Validation failed",
            fieldFieldErrors.stream()
                .map(getValidationErrorFromFieldError)
                .collect(Collectors.toList())
        );
    }

    private static Function<FieldError, ValidationError> getValidationErrorFromFieldError =
        fieldError -> new ValidationError(
            fieldError.getField(),
            fieldError.getCode(),
            fieldError.getObjectName(),
            fieldError.getDefaultMessage()
        );
}
