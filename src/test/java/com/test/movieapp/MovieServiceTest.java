package com.test.movieapp;

import com.test.movieapp.model.Movie;
import com.test.movieapp.repository.MovieRepository;
import com.test.movieapp.service.MovieService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MovieServiceTest {

    @Mock
    private MovieRepository movieRepository;

    @InjectMocks
    private MovieService movieService;

    private Movie movie1;
    private Movie movie2;
    private List<Movie> allMovies;

    @BeforeEach
    public void setUp() {
        movie1 = new Movie();
        movie1.setId(1L);
        movie2 = new Movie();
        movie2.setId(2L);
        allMovies = Arrays.asList(movie1, movie2);
    }

    @Test
    public void getAllMoviesTest() {
        when(movieRepository.findAll()).thenReturn(allMovies);

        List<Movie> result = movieService.getAllMovies();
        assertEquals(allMovies.size(), result.size());

        verify(movieRepository, times(1)).findAll();
    }

    @Test
    public void getMovieByIdTest() {
        when(movieRepository.findById(1L)).thenReturn(Optional.of(movie1));

        Optional<Movie> result = movieService.getMovieById(1L);
        assertTrue(result.isPresent());
        assertEquals(movie1.getId(), result.get().getId());

        verify(movieRepository, times(1)).findById(1L);
    }

    @Test
    public void createMovieTest() {
        when(movieRepository.save(any(Movie.class))).thenReturn(movie1);

        Movie result = movieService.createMovie(movie1);
        assertEquals(movie1.getId(), result.getId());

        verify(movieRepository, times(1)).save(movie1);
    }

    @Test
    public void deleteAllMoviesTest() {
        doNothing().when(movieRepository).deleteAll();

        movieService.deleteAllMovies();

        verify(movieRepository, times(1)).deleteAll();
    }
}