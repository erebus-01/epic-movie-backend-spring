package com.programing.series.config;


import com.programing.series.model.CrewMapper;
import com.programing.series.model.EpisodesMapper;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name = "CREW-SERVICE", url = "http://localhost:9000")
public interface FeignClientCrew {

    @GetMapping("/api/crew/{id}")
    CrewMapper findCrewById(@PathVariable("id") UUID id);

}
