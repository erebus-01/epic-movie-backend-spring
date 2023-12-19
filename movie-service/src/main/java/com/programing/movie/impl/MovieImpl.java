package com.programing.movie.impl;

import com.programing.movie.dto.MovieRequest;
import com.programing.movie.dto.MovieResponse;
import com.programing.movie.model.Movie;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MovieImpl {

    MovieResponse save(MovieRequest request);
    List<MovieResponse> saveAll(List<MovieRequest> requests);
    MovieResponse updateById(MovieRequest request, UUID id);
    List<MovieResponse> getAllMovie();
    MovieResponse findMovieById(UUID id);
    String deleteById(UUID id);

}
