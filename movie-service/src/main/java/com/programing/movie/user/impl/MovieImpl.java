package com.programing.movie.user.impl;

import com.programing.movie.user.dto.MovieRequest;
import com.programing.movie.user.dto.MovieResponse;

import java.util.List;
import java.util.UUID;

public interface MovieImpl {

    MovieResponse save(MovieRequest request);
    List<MovieResponse> saveAll(List<MovieRequest> requests);
    MovieResponse updateById(MovieRequest request, UUID id);
    List<MovieResponse> getAllMovie();
    MovieResponse findMovieById(UUID id);
    String deleteById(UUID id);

}
