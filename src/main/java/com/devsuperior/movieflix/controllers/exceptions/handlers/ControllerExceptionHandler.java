package com.devsuperior.movieflix.controllers.exceptions.handlers;

import com.devsuperior.movieflix.controllers.exceptions.OAuthCustomError;
import com.devsuperior.movieflix.services.exceptions.ForbiddenException;
import com.devsuperior.movieflix.services.exceptions.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionHandler {
    
    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<OAuthCustomError> forbidden(ForbiddenException exception) {
        var status = HttpStatus.FORBIDDEN;
        var error = new OAuthCustomError("Forbidden", exception.getMessage());
        
        return ResponseEntity.status(status).body(error);
    }
    
    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<OAuthCustomError> unauthorized(UnauthorizedException exception) {
        var status = HttpStatus.UNAUTHORIZED;
        var error = new OAuthCustomError("Unauthorized", exception.getMessage());
        
        return ResponseEntity.status(status).body(error);
    }
}
