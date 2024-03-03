package com.test.movieapp;

import com.test.movieapp.controller.MovieController;
import com.test.movieapp.model.Movie;
import com.test.movieapp.service.MovieService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class MovieControllerTest {

    private MockMvc mockMvc;

    @Mock
    private MovieService movieService;

    @InjectMocks
    private MovieController movieController;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(movieController).build();
    }

    @Test
    void shouldFetchAllMovies() throws Exception {
        when(movieService.getAllMovies()).thenReturn(Arrays.asList(new Movie(), new Movie()));

        mockMvc.perform(get("/api/movies"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));

        verify(movieService, times(1)).getAllMovies();
    }

    @Test
    void shouldFetchMovieById() throws Exception {
        Movie movie = new Movie();
        movie.setId(1L);

        when(movieService.getMovieById(movie.getId())).thenReturn(Optional.of(movie));

        mockMvc.perform(get("/api/movies/{id}", movie.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)));

        verify(movieService, times(1)).getMovieById(movie.getId());
    }

    @Test
    void shouldNotFindMovieById() throws Exception {
        when(movieService.getMovieById(1L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/movies/1"))
                .andExpect(status().isNotFound());

        verify(movieService, times(1)).getMovieById(1L);
    }

    @Test
    void shouldCreateMovie() throws Exception {
        Movie movie = new Movie();
        movie.setId(1L);
        movie.setTitle("Test Title");

        when(movieService.createMovie(any(Movie.class))).thenReturn(movie);

        mockMvc.perform(post("/api/movies")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\": 1, \"title\": \"Test Title\"}"))
                .andExpect(status().isCreated());

        verify(movieService, times(1)).createMovie(any(Movie.class));
    }

    @Test
    void shouldDeleteAllMovies() throws Exception {
        doNothing().when(movieService).deleteAllMovies();

        mockMvc.perform(delete("/api/movies"))
                .andExpect(status().isNoContent());

        verify(movieService, times(1)).deleteAllMovies();
    }
}