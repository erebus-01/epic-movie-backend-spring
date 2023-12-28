package com.programing.series.controller;

import com.programing.series.dto.SeriesRequest;
import com.programing.series.dto.SeriesResponse;
import com.programing.series.service.SeriesService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.Path;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/series")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "*")
public class SeriesController {

    private final SeriesService service;

    @PostMapping
    public ResponseEntity<SeriesResponse> save(@RequestBody SeriesRequest request) {
        SeriesResponse series = service.save(request);
        return new ResponseEntity<>(series, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<SeriesResponse>> getAll() {
        List<SeriesResponse> seriesResponses = service.getAll();
        return new ResponseEntity<>(seriesResponses, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @RolesAllowed("ROLE_MANAGER")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<SeriesResponse> getOne(@PathVariable("id") UUID id) {
        log.info("Checking access for user with roles: {}", SecurityContextHolder.getContext().getAuthentication().getAuthorities());
        SeriesResponse seriesResponses = service.getOne(id);
        return new ResponseEntity<>(seriesResponses, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SeriesResponse> update(@PathVariable("id") UUID id, @RequestBody SeriesRequest request) {
        SeriesResponse seriesResponses = service.update(request, id);
        return new ResponseEntity<>(seriesResponses, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") UUID id) {
        String seriesDelete = service.delete(id);
        return new ResponseEntity<>(seriesDelete, HttpStatus.OK);
    }


}
