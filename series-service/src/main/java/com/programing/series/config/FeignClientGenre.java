package com.programing.series.config;

import com.programing.series.model.EpisodesMapper;
import com.programing.series.model.GenreMapper;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name = "GENRE-SERVICE", url = "http://localhost:9000")
public interface FeignClientGenre {

    @GetMapping("/api/genre/{id}")
    GenreMapper getGenreById(@PathVariable("id") Long id);

}
