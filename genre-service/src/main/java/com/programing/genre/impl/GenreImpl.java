package com.programing.genre.impl;

import com.programing.genre.dto.GenreRequest;
import com.programing.genre.dto.GenreResponse;
import com.programing.genre.model.Genre;
import com.programing.genre.model.MovieMapper;

import java.util.List;

public interface GenreImpl {

    GenreResponse save(GenreRequest request);
    GenreResponse getGenreById(Long id);
    List<GenreResponse> saveListGenre(List<GenreRequest> request);
    List<MovieMapper> getById(Long id);
    GenreResponse update(GenreRequest request, Long id);
    List<GenreResponse> getAll();
    String deleteById(Long id);

}
