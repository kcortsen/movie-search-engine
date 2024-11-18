package com.example.searchengine.controller;

import com.example.searchengine.model.Movie;
import com.example.searchengine.model.MovieSearchResponse;
import com.example.searchengine.service.MovieService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/search")
    public MovieSearchResponse searchMovies(@RequestParam("title") String title) {
        return movieService.getSearchedMovies(title);
    }
}
