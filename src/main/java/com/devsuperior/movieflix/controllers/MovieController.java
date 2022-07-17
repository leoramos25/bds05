package com.devsuperior.movieflix.controllers;

import com.devsuperior.movieflix.dtos.MovieByGenreDTO;
import com.devsuperior.movieflix.dtos.MovieDTO;
import com.devsuperior.movieflix.dtos.ReviewDTO;
import com.devsuperior.movieflix.services.MovieService;
import com.devsuperior.movieflix.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/movies")
public class MovieController {
    
    @Autowired
    private MovieService movieService;
    
    @Autowired
    private ReviewService reviewService;
    
    @GetMapping
    public ResponseEntity<Page<MovieByGenreDTO>> findPageOfMovies(
            @RequestParam(value = "genreId", defaultValue = "0") Long genreId,
            Pageable pageable
    ) {
        var moviesPage = movieService.searchAllMovies(genreId, pageable);
        return ResponseEntity.ok().body(moviesPage);
    }
    
    @GetMapping(value = "/{id}")
    public ResponseEntity<MovieDTO> findById(@PathVariable(value = "id") Long id) {
        var movie = movieService.findById(id);
        return ResponseEntity.ok().body(movie);
    }
    
    @GetMapping(value = "/{id}/reviews")
    public ResponseEntity<List<ReviewDTO>> findReviewsOfCurrentUser(@PathVariable(value = "id") Long id) {
        var reviews = reviewService.reviewOfCurrentUser(id);
        return ResponseEntity.ok().body(reviews);
    }
}
