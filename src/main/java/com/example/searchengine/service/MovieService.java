package com.example.searchengine.service;

import com.example.searchengine.model.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

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

    public MovieSearchResponse getSearchedMovies(String title) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("apikey", apiKey)
                        .queryParam("s", title)
                        .build())
                .retrieve()
                .bodyToMono(MovieSearchResponse.class)
                .block();
    }
}