package com.devsuperior.movieflix.services;

import com.devsuperior.movieflix.dtos.ReviewDTO;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.entities.Review;
import com.devsuperior.movieflix.repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewService {
    
    @Autowired
    private ReviewRepository reviewRepository;
    
    @Autowired
    private AuthService authService;
    
    @Transactional(readOnly = true)
    public List<ReviewDTO> reviewOfCurrentUser(Long id) {
        var user = authService.authenticated();
        var reviews = reviewRepository.search(user, id);
        
        return reviews.stream().map(review -> new ReviewDTO(review, user))
                .collect(Collectors.toList());
    }
    
    @Transactional
    public ReviewDTO insert(ReviewDTO dto) {
        var user = authService.authenticated();
        var review = new Review();
        review.setText(dto.getText());
        review.setMovie(new Movie(dto.getMovieId(), null, null, null, null, null, null));
        review = reviewRepository.save(review);
        return new ReviewDTO(review, user);
    }
}
