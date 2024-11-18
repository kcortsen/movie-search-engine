package com.example.searchengine.service;

import com.example.searchengine.model.*;
import com.example.searchengine.repository.MovieRepo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieService {

    private final WebClient webClient;
    private final String apiKey;
    private final MovieRepo movieRepo;

    public MovieService(WebClient.Builder webClientBuilder, @Value("${OMDB_API_KEY}") String apiKey, MovieRepo movieRepo) {
        this.webClient = webClientBuilder
                .baseUrl("https://www.omdbapi.com/")
                .build();
        this.apiKey = apiKey;
        this.movieRepo = movieRepo;
    }

    public MovieSearchResponse getSearchedMovies(String title) {
        MovieSearchResponse response = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("apikey", apiKey)
                        .queryParam("s", title)
                        .build())
                .retrieve()
                .bodyToMono(MovieSearchResponse.class)
                .block();
        saveMovies(response);

        return response;
    }

    public void saveMovies(MovieSearchResponse movieSearchResponse) {
        if (movieSearchResponse.getSearch() != null) {
            for (Movie movie : movieSearchResponse.getSearch()) {
                if (!movieRepo.existsById(movie.getImdbID())) {
                    movieRepo.save(movie);
                }
            }
        }
    }
}
