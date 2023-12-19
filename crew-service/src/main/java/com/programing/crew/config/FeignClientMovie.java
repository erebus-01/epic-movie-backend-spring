package com.programing.crew.config;

import com.programing.crew.model.MovieMapper;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name = "MOVIE-SERVICE", url = "http://localhost:9040")
public interface FeignClientMovie {


    @GetMapping("/api/movie/{id}")
    MovieMapper findMovieById(@PathVariable("id") UUID id);

}
