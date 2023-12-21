package com.programing.crew.impl;

import com.programing.crew.dto.CrewRequest;
import com.programing.crew.dto.CrewResponse;
import com.programing.crew.model.MovieMapper;
import com.programing.crew.model.TypeCrew;
import com.programing.crew.repository.CrewRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CrewImplement {

    List<CrewResponse> getAllCrew();
    CrewResponse findCrewById(UUID id);
    Optional<CrewResponse> save(CrewRequest request);
    List<CrewResponse> saveListCrew(List<CrewRequest> request);
    CrewResponse update(CrewRequest request, UUID id);
    String deleteCrewById(UUID id);
    List<MovieMapper> findMovieMapperByCrewId(UUID id);
    List<CrewResponse> findByCrewActor();
    List<CrewResponse> findByCrewDirector();



}
