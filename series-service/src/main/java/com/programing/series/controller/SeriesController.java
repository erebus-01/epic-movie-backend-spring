package com.programing.series.controller;

import com.programing.series.dto.SeriesRequest;
import com.programing.series.dto.SeriesResponse;
import com.programing.series.service.SeriesService;
import jakarta.ws.rs.Path;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/series")
@RequiredArgsConstructor
public class SeriesController {

    private final SeriesService service;

    @PostMapping
    private ResponseEntity<SeriesResponse> save(@RequestBody SeriesRequest request) {
        SeriesResponse series = service.save(request);
        return new ResponseEntity<>(series, HttpStatus.CREATED);
    }

    @GetMapping
    private ResponseEntity<List<SeriesResponse>> getAll() {
        List<SeriesResponse> seriesResponses = service.getAll();
        return new ResponseEntity<>(seriesResponses, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    private ResponseEntity<SeriesResponse> getOne(@PathVariable("id") UUID id) {
        SeriesResponse seriesResponses = service.getOne(id);
        return new ResponseEntity<>(seriesResponses, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<String> delete(@PathVariable("id") UUID id) {
        String seriesDelete = service.delete(id);
        return new ResponseEntity<>(seriesDelete, HttpStatus.OK);
    }


}
