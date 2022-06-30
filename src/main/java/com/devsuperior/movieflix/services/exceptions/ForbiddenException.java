package com.devsuperior.movieflix.services.exceptions;

public class ForbiddenException extends RuntimeException {
    
    private static final long serialVersionUID = -5958735068400237245L;
    
    public ForbiddenException(String message) {
        super(message);
    }
}
