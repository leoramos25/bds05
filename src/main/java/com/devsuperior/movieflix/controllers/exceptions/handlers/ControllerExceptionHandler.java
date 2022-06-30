package com.devsuperior.movieflix.controllers.exceptions.handlers;

import com.devsuperior.movieflix.controllers.exceptions.OAuthCustomError;
import com.devsuperior.movieflix.controllers.exceptions.StandardError;
import com.devsuperior.movieflix.services.exceptions.ForbiddenException;
import com.devsuperior.movieflix.services.exceptions.ResourceNotFoundException;
import com.devsuperior.movieflix.services.exceptions.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

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
    
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardError> entityNotFound(ResourceNotFoundException exception, HttpServletRequest request) {
        var status = HttpStatus.NOT_FOUND;
        var error = new StandardError();
        
        error.setTimestamp(Instant.now());
        error.setStatus(status.value());
        error.setError("Resource not found");
        error.setMessage(exception.getMessage());
        error.setPath(request.getRequestURI());
        
        return ResponseEntity.status(status).body(error);
    }
}
