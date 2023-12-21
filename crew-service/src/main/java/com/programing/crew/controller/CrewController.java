package com.programing.crew.controller;

import com.programing.crew.dto.CrewRequest;
import com.programing.crew.dto.CrewResponse;
import com.programing.crew.model.Crew;
import com.programing.crew.model.MovieMapper;
import com.programing.crew.model.TypeCrew;
import com.programing.crew.service.CrewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/crew")
@Slf4j
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,RequestMethod.DELETE})
public class CrewController {
    private final CrewService service;

    @GetMapping()
    private ResponseEntity<List<CrewResponse>> getAllCrew() {
        List<CrewResponse> crewResponse = service.getAllCrew();
        return new ResponseEntity<>(crewResponse, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CrewResponse> findCrewById(@PathVariable UUID id) {
        CrewResponse crew = service.findCrewById(id);
        return new ResponseEntity<>(crew, HttpStatus.OK);
    }

    @GetMapping("/movie/{id}")
    private ResponseEntity<List<MovieMapper>> getAllMovieOfCrew(@PathVariable("id") UUID id) {
        List<MovieMapper> crewResponse = service.findMovieMapperByCrewId(id);
        return new ResponseEntity<>(crewResponse, HttpStatus.OK);
    }

    @GetMapping("/crew-actor")
    private ResponseEntity<List<CrewResponse>> findByTypeCrew() {
        List<CrewResponse> crewResponse = service.findByCrewActor();
        return new ResponseEntity<>(crewResponse, HttpStatus.OK);
    }

    @GetMapping("/crew-director")
    private ResponseEntity<List<CrewResponse>> findByTypeDirector() {
        List<CrewResponse> crewResponse = service.findByCrewDirector();
        return new ResponseEntity<>(crewResponse, HttpStatus.OK);
    }


    @PostMapping()
    private ResponseEntity<Optional<CrewResponse>> save(@RequestBody CrewRequest request) {
        Optional<CrewResponse> crewResponse = service.save(request);
        return new ResponseEntity<>(crewResponse, HttpStatus.CREATED);
    }

    @PostMapping("/list-data")
    private ResponseEntity<List<CrewResponse>> saveListData(@RequestBody List<CrewRequest> request) {
        List<CrewResponse> crewResponses = service.saveListCrew(request);
        return new ResponseEntity<>(crewResponses, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    private ResponseEntity<CrewResponse> update(@RequestBody CrewRequest request, @PathVariable UUID id) {
        CrewResponse crew = service.update(request, id);
        return new ResponseEntity<>(crew, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<String> delete(@PathVariable("id") UUID id) {
        String crew = service.deleteCrewById(id);
        return new ResponseEntity<>(crew, HttpStatus.OK);
    }

}
