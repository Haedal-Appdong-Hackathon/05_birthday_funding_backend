package com.just.birthdayFunding.core.exception;

import jakarta.servlet.ServletException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestControllerAdvice
@Slf4j
@RequiredArgsConstructor
public class CommonExceptionHandler {
    private final GetCallingClass getCallingClass;

    @ExceptionHandler(value = ResponseStatusException.class)
    public ResponseEntity<ErrorResponse> ResponseStatusExceptionHandler(ResponseStatusException e){
        log.error("Exception occurred in: {}", getCallingClass.call(e));
        log.error("ResponseStatusException : {}", e.getMessage());
        return new ResponseEntity<>(new ErrorResponse(e.getStatusCode().toString(), e.getReason()), e.getStatusCode());
    }
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> illegalArgumentExceptionHandler(IllegalArgumentException e){
        log.error("Exception occurred in: {}", getCallingClass.call(e));
        log.error("IllegalArgumentException : {}", e.getMessage());
        return new ResponseEntity<>(new ErrorResponse("IllegalArgumentException", e.getMessage()), BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> responseStatusExceptionHandler(ResponseStatusException e){
        return new ResponseEntity<>(new ErrorResponse(e.getMessage(), e.getReason()), e.getStatusCode());
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e){
        log.error("Exception occurred in: {}", getCallingClass.call(e));
        log.error("MethodArgumentNotValidException : {}", e.getMessage());
        BindingResult bindingResult = e.getBindingResult();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();

        Map<String, String> errors = new HashMap<>();
        for (FieldError fieldError : fieldErrors)
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());

        return new ResponseEntity<>(new ErrorResponse("MethodArgumentNotValidException", errors.toString()), BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> servletExceptionHandler(ServletException e){
        log.error("Exception occurred in: {}", getCallingClass.call(e));
        log.error("ServletException : {}", e.getMessage());
        return new ResponseEntity<>(new ErrorResponse("NoSuchElementException", e.getMessage()), BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> invalidDataAccessApiUsageExceptionHandler(InvalidDataAccessApiUsageException e){
        log.error("Exception occurred in: {}", getCallingClass.call(e));
        log.error("InvalidDataAccessApiUsageException : {}", e.getMessage());
        return new ResponseEntity<>(new ErrorResponse("InvalidDataAccessApiUsageException", e.getMessage()), BAD_REQUEST);
    }


    @ExceptionHandler
    public ResponseEntity<ErrorResponse> noSuchElementExceptionHandler(NoSuchElementException e) {
        log.error("Exception occurred in: {}", getCallingClass.call(e));
        log.error("NoSuchElementException : {}", e.getMessage());
        return new ResponseEntity<>(new ErrorResponse("NoSuchElementException", e.getMessage()), BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> jwtExceptionHandler(JwtException e) {
        log.error("Exception occurred in: {}", getCallingClass.call(e));
        log.error("JwtException : {}", e.getMessage());
        return new ResponseEntity<>(new ErrorResponse("JwtException", e.getMessage()), BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> runtimeExceptionHandler(RuntimeException e) {
        log.error("Exception occurred in: {}", getCallingClass.call(e));
        log.error("RuntimeException : {}", e.getMessage());
        return new ResponseEntity<>(new ErrorResponse("RuntimeException", e.getMessage()), BAD_REQUEST);
    }

}
