package com.example.searchengine.repository;

import com.example.searchengine.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepo extends JpaRepository<Movie, String> {

}
