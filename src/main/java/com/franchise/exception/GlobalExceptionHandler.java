package com.franchise.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles ResourceNotFoundException and returns a response with status 404 (Not Found).
     * @param ex The ResourceNotFoundException thrown.
     * @return Mono<ResponseEntity<Map<String, Object>>> The response entity with error details.
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public Mono<ResponseEntity<Map<String, Object>>> handleResourceNotFoundException(ResourceNotFoundException ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", ex.getMessage());
        body.put("status", HttpStatus.NOT_FOUND.value());
        
        return Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND).body(body));
    }

    /**
     * Handles generic exceptions and returns a response with status 500 (Internal Server Error).
     * @param ex The Exception thrown.
     * @return Mono<ResponseEntity<Map<String, Object>>> The response entity with error details.
     */
    @ExceptionHandler(Exception.class)
    public Mono<ResponseEntity<Map<String, Object>>> handleGlobalException(Exception ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", "An unexpected error occurred");
        body.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        
        return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body));
    }
}