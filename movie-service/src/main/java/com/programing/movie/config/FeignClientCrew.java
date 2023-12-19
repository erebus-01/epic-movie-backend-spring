package com.programing.movie.config;


import com.programing.movie.model.CrewMapper;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;
import java.util.UUID;

@FeignClient(name = "CREW-SERVICE", url = "http://localhost:9000")
public interface FeignClientCrew {

    @GetMapping("/api/crew/{id}")
    CrewMapper findCrewById(@PathVariable("id") UUID id);

}
