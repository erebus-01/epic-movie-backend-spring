package com.programing.genre.controller;

import com.programing.genre.dto.GenreRequest;
import com.programing.genre.dto.GenreResponse;
import com.programing.genre.model.Genre;
import com.programing.genre.model.MovieMapper;
import com.programing.genre.service.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/genre")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class GenreController {

    private final GenreService service;

    @GetMapping
    private ResponseEntity<List<GenreResponse>> getAll() {
        List<GenreResponse> genreResponseList = service.getAll();
        return new ResponseEntity<>(genreResponseList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    private ResponseEntity<GenreResponse> getGenreById(@PathVariable("id") Long id) {
        GenreResponse genreResponse = service.getGenreById(id);
        return new ResponseEntity<>(genreResponse, HttpStatus.OK);
    }

    @GetMapping("/movie/{id}")
    private ResponseEntity<List<MovieMapper>> getMovieByIdGenre(@PathVariable Long id) {
        List<MovieMapper> movieMapperList = service.getById(id);
        return new ResponseEntity<>(movieMapperList, HttpStatus.OK);
    }

    @PostMapping
    private ResponseEntity<GenreResponse> save(@RequestBody GenreRequest request) {
        GenreResponse genreResponse = service.save(request);
        return new ResponseEntity<>(genreResponse, HttpStatus.CREATED);
    }

    @PostMapping("/list-data")
    private ResponseEntity<List<GenreResponse>> saveListData(@RequestBody List<GenreRequest> request) {
        List<GenreResponse> genreResponseList = service.saveListGenre(request);
        return new ResponseEntity<>(genreResponseList, HttpStatus.CREATED);
    }



}
