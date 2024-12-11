package com.franchise.exception;

/**
 * Custom exception for resource not found scenarios.
 * Extends RuntimeException to indicate that this is an unchecked exception.
 */
public class ResourceNotFoundException extends RuntimeException {

    /**
     * Constructor for ResourceNotFoundException.
     * @param message The detail message explaining the reason for the exception.
     */
    public ResourceNotFoundException(String message) {
        super(message);
    }
}