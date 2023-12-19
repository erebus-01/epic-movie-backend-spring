package com.programing.movie.config;

import com.programing.movie.model.GenreMapper;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "GENRE-SERVICE", url = "http://localhost:9000")
public interface FeignClientGenre {

    @GetMapping("/api/genre/{id}")
    GenreMapper getGenreById(@PathVariable("id") Long id);

}
