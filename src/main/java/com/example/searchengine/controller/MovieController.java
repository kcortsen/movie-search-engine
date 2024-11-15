package com.example.searchengine.controller;

import com.example.searchengine.model.Movie;
import com.example.searchengine.service.MovieService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movies")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/search")
    public Movie searchMovie(@RequestParam String title) {
        return movieService.getSearchedMovie(title);
    }
}
