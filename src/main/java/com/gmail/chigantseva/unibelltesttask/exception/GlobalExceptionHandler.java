package com.gmail.chigantseva.unibelltesttask.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    protected ResponseEntity<ResponseExceptionModel> handleResourceNotFoundException(ResourceNotFoundException ex) {
        return new ResponseEntity<>(new ResponseExceptionModel(ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ContactAlreadyExistsException.class)
    protected ResponseEntity<ResponseExceptionModel> handleContactAlreadyExistsException(ContactAlreadyExistsException ex) {
        return new ResponseEntity<>(new ResponseExceptionModel(ex.getMessage()), HttpStatus.CONFLICT);
    }

    @ExceptionHandler({UnknownParamException.class, NumberConversionError.class, UnknownParamException.class, IllegalArgumentException.class})
    protected ResponseEntity<ResponseExceptionModel> handlePathVariableError(RuntimeException ex) {
        return new ResponseEntity<>(new ResponseExceptionModel(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage()));

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @Setter
    @Getter
    @AllArgsConstructor
    private static class ResponseExceptionModel {
        private String message;
    }

}
