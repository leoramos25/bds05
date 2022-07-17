package com.devsuperior.movieflix.services;

import com.devsuperior.movieflix.dtos.MovieByGenreDTO;
import com.devsuperior.movieflix.dtos.MovieDTO;
import com.devsuperior.movieflix.repositories.GenreRepository;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MovieService {
    
    @Autowired
    private MovieRepository movieRepository;
    
    @Autowired
    private GenreRepository genreRepository;
    
    @Transactional(readOnly = true)
    public Page<MovieByGenreDTO> searchAllMovies(Long genreId, Pageable pageable) {
        var genresList = genreId == 0 ? null : List.of(genreRepository.getOne(genreId));
        var movies = movieRepository.searchMovieByGenre(genresList, pageable);
        movieRepository.searchProductsByCategories(movies.getContent());
        return movies.map(MovieByGenreDTO::new);
    }
    
    public MovieDTO findById(Long id) {
        var movie = movieRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
        
        return new MovieDTO(movie, movie.getGenre());
    }
}
