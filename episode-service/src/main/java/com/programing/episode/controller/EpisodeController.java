package com.programing.episode.controller;

import com.programing.episode.dto.EpisodeRequest;
import com.programing.episode.dto.EpisodeResponse;
import com.programing.episode.service.EpisodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/episode")
@RequiredArgsConstructor
public class EpisodeController {

    private final EpisodeService service;

    @PostMapping
    private ResponseEntity<EpisodeResponse> save(@RequestBody EpisodeRequest request) {
        EpisodeResponse episodeResponse = service.save(request);
        return new ResponseEntity<>(episodeResponse, HttpStatus.CREATED);
    }

    @PostMapping("/list-data")
    private ResponseEntity<List<EpisodeResponse>> saveAll(@RequestBody List<EpisodeRequest> request) {
        List<EpisodeResponse> episodeResponses = service.saveAll(request);
        return new ResponseEntity<>(episodeResponses, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    private ResponseEntity<EpisodeResponse> getOne(@PathVariable("id") UUID id) {
        EpisodeResponse episodeResponses = service.getOne(id);
        return new ResponseEntity<>(episodeResponses, HttpStatus.OK
        );
    }

    @GetMapping("/season/{id}")
    private ResponseEntity<List<EpisodeResponse>> getEpisodeForSeason(@PathVariable("id") Long id) {
        List<EpisodeResponse> episodeResponses = service.getAllMovieBySeasonId(id);
        return new ResponseEntity<>(episodeResponses, HttpStatus.OK);
    }

    @GetMapping("/series/{id}")
    private ResponseEntity<List<EpisodeResponse>> getEpisodeForSeries(@PathVariable("id") UUID id) {
        List<EpisodeResponse> episodeResponses = service.getEpisodesBySeriesId(id);
        return new ResponseEntity<>(episodeResponses, HttpStatus.OK);
    }

    @GetMapping("/series/{seriesId}/{seasonId}")
    private ResponseEntity<List<EpisodeResponse>> getEpisodeForSeries(@PathVariable("seriesId") UUID seriesId, @PathVariable("seasonId") Long seasonId) {
        List<EpisodeResponse> episodeResponses = service.getEpisodesBySeriesAndSeason(seriesId, seasonId);
        return new ResponseEntity<>(episodeResponses, HttpStatus.OK);
    }


    @PutMapping("/{id}")
    private ResponseEntity<EpisodeResponse> update(@RequestBody EpisodeRequest request, @PathVariable("id") UUID id) {
        EpisodeResponse episodeResponses = service.update(request, id);
        return new ResponseEntity<>(episodeResponses, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<String> delete(@PathVariable("id") UUID id) {
        String episodeDelete = service.delete(id);
        return new ResponseEntity<>(episodeDelete, HttpStatus.OK);
    }

}
