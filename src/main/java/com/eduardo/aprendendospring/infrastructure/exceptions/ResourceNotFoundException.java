package com.eduardo.aprendendospring.infrastructure.exceptions;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String mensage, Throwable cause) {
        super(mensage, cause);
    }
    public ResourceNotFoundException(String mensage) {
        super(mensage);
    }
}
