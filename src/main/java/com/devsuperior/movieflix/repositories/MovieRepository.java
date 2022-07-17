package com.devsuperior.movieflix.repositories;

import com.devsuperior.movieflix.entities.Genre;
import com.devsuperior.movieflix.entities.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    
    @Query("SELECT DISTINCT movie FROM Movie movie INNER JOIN movie.genre genre WHERE "
            + "(COALESCE(:genres) IS NULL OR genre IN :genres) "
            + "ORDER BY movie.title")
    Page<Movie> searchMovieByGenre(List<Genre> genres, Pageable pageable);
    
    @Query("SELECT movie FROM Movie movie JOIN FETCH movie.genre WHERE movie IN :movies")
    List<Movie> searchProductsByCategories(List<Movie> movies);
}
