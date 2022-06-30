package com.devsuperior.movieflix.services.exceptions;

public class UnauthorizedException extends RuntimeException {
    
    private static final long serialVersionUID = -8196611923167275001L;
    
    public UnauthorizedException(String message) {
        super(message);
    }
}
