package com.example.searchengine.service;

import com.example.searchengine.model.Movie;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@Service
public class MovieService {

    private final WebClient webClient;
    private final String apiKey;

    public MovieService(WebClient.Builder webClientBuilder, @Value("${OMDB_API_KEY}") String apiKey) {
        this.webClient = webClientBuilder
                .baseUrl("https://www.omdbapi.com/")
                .build();
        this.apiKey = apiKey;
    }

    public Movie getSearchedMovie(String title) {
        System.out.println("API Key: " + apiKey);

        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("apikey", apiKey)
                        .queryParam("t", title)
                        .build())
                .retrieve()
                .bodyToMono(Movie.class)
                .block();
    }
}