package com.devsuperior.movieflix.services;

import com.devsuperior.movieflix.entities.User;
import com.devsuperior.movieflix.repositories.UserRepository;
import com.devsuperior.movieflix.services.exceptions.ForbiddenException;
import com.devsuperior.movieflix.services.exceptions.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    
    private static final String INVALID_USER = "Invalid user";
    
    private static final String DENIED_ACCESS = "Denied access";
    
    @Autowired
    private UserRepository userRepository;
    
    public User authenticated() {
        try {
            var email = SecurityContextHolder.getContext().getAuthentication().getName();
            return userRepository.findByEmail(email);
        } catch (Exception error) {
            throw new UnauthorizedException(INVALID_USER);
        }
    }
    
    public void isAuthenticatedUser(Long userId) {
        var authenticatedUser = authenticated();
        
        if (!authenticatedUser.getId().equals(userId)) {
            throw new ForbiddenException(DENIED_ACCESS);
        }
    }
}
