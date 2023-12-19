package com.programing.series.config;

import com.programing.series.model.EpisodesMapper;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name = "EPISODE-SERVICE", url = "http://localhost:9000")
public interface FeignClientEpisodes {

    @GetMapping("/api/episode/{id}")
    EpisodesMapper getOne(@PathVariable("id") UUID id);


}
