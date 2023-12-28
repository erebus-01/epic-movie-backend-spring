package com.programing.movie.user.controller;

import com.programing.movie.user.dto.MovieRequest;
import com.programing.movie.user.dto.MovieResponse;
import com.programing.movie.user.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/movie")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService service;

    @GetMapping
    private ResponseEntity<List<MovieResponse>> getAllMovie() {
        List<MovieResponse> movieResponses = service.getAllMovie();
        return new ResponseEntity<>(movieResponses, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    private ResponseEntity<MovieResponse> getMovieById(@PathVariable UUID id) {
        MovieResponse movieResponse = service.findMovieById(id);
        return new ResponseEntity<>(movieResponse, HttpStatus.OK);
    }

    @PostMapping
    private ResponseEntity<MovieResponse> createNewMovie(@RequestBody MovieRequest request) {
        MovieResponse movie = service.save(request);
        return new ResponseEntity<>(movie, HttpStatus.CREATED);
    }

    @PostMapping("/list-data")
    private ResponseEntity<List<MovieResponse>> createNewMovie(@RequestBody List<MovieRequest> request) {
        List<MovieResponse> movie = service.saveAll(request);
        return new ResponseEntity<>(movie, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    private ResponseEntity<MovieResponse> updateById(@RequestBody MovieRequest request, @PathVariable UUID id) {
        MovieResponse movie = service.updateById(request, id);
        return new ResponseEntity<>(movie, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<String> deleteById(@PathVariable UUID id) {
        return new ResponseEntity<>(service.deleteById(id), HttpStatus.OK);
    }


}
