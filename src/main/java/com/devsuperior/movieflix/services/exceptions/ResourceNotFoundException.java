package com.devsuperior.movieflix.services.exceptions;

public class ResourceNotFoundException extends RuntimeException {
    
    private static final long serialVersionUID = -7079125678677760755L;
    
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
