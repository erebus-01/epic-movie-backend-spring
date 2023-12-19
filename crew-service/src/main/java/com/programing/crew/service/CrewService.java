package com.programing.crew.service;

import com.programing.crew.config.FeignClientMovie;
import com.programing.crew.dto.CrewRequest;
import com.programing.crew.dto.CrewResponse;
import com.programing.crew.impl.CrewImplement;
import com.programing.crew.model.Crew;
import com.programing.crew.model.MovieMapper;
import com.programing.crew.repository.CrewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CrewService implements CrewImplement {

    private final CrewRepository repository;
    private final WebClient.Builder webClientConfig;
    private final FeignClientMovie feignClientMovie;

    @Override
    public List<CrewResponse> getAllCrew() {
        List<Crew> crews = repository.findAll();
        return crews.stream().map(this::mapToCrewResponse).collect(Collectors.toList());
    }

    @Override
    public CrewResponse findCrewById(UUID id) {
        Optional<Crew> crewOptional = repository.findById(id);

        if (crewOptional.isPresent()) {
            Crew crew = crewOptional.get();

            return mapToCrewResponse(crew);
        }

        return null;
    }


    @Override
    public Optional<CrewResponse> save(CrewRequest request) {
        Crew crew = Crew.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .dateOfBirth(request.getDateOfBirth())
                .nation(request.getNation())
                .gender(request.getGender())
                .typeCrew(request.getTypeCrew())
                .description(request.getDescription())
                .image(request.getImage())
                .build();

        repository.save(crew);

        return Optional.ofNullable(mapToCrewResponse(crew));
    }

    @Override
    public List<CrewResponse> saveListCrew(List<CrewRequest> request) {
        List<CrewResponse> crewResponsesList = new ArrayList<>();
        if(!request.isEmpty()) {
            for(CrewRequest crewRequest : request) {
                Crew crew = Crew.builder()
                        .firstname(crewRequest.getFirstname())
                        .lastname(crewRequest.getLastname())
                        .dateOfBirth(crewRequest.getDateOfBirth())
                        .nation(crewRequest.getNation())
                        .gender(crewRequest.getGender())
                        .typeCrew(crewRequest.getTypeCrew())
                        .description(crewRequest.getDescription())
                        .image(crewRequest.getImage())
                        .build();

                repository.save(crew);

                crewResponsesList.add(mapToCrewResponse(crew));
            }
        }
        return crewResponsesList;
    }

    @Override
    public CrewResponse update(CrewRequest request, UUID id) {
        Optional<Crew> crewOptional = repository.findById(id);
        if(crewOptional.isPresent()) {
            Crew crew = Crew.builder()
                    .firstname(request.getFirstname())
                    .lastname(request.getLastname())
                    .dateOfBirth(request.getDateOfBirth())
                    .nation(request.getNation())
                    .gender(request.getGender())
                    .typeCrew(request.getTypeCrew())
                    .description(request.getDescription())
                    .image(request.getImage())
                    .build();

            repository.save(crew);

            return mapToCrewResponse(crew);
        }
        return null;
    }

    @Override
    public String deleteCrewById(UUID id) {
        Optional<Crew> crew = repository.findById(id);
        if(crew.isPresent()) {
            repository.deleteById(id);
            return "Delete crew with id" + id + " successfully";
        }
        return "Not found crew with id: " + id;
    }

    @Override
    public List<MovieMapper> findMovieMapperByCrewId(UUID id) {


        Optional<Crew> crewOptional = repository.findById(id);

        if (crewOptional.isPresent()) {
            Crew crew = crewOptional.get();

            List<MovieMapper> movieMapperList = crew.getMovies().stream().map(feignClientMovie::findMovieById).toList();


            return movieMapperList;
        }

        return null;
    }

    private CrewResponse mapToCrewResponse(Crew crew)   {
        return CrewResponse.builder()
                .id(crew.getId())
                .firstname(crew.getFirstname())
                .lastname(crew.getLastname())
                .dateOfBirth(crew.getDateOfBirth())
                .nation(crew.getNation())
                .gender(crew.getGender())
                .typeCrew(crew.getTypeCrew())
                .description(crew.getDescription())
                .image(crew.getImage())
                .createAt(crew.getCreateAt())
                .movies(crew.getMovies())
                .updateAt(crew.getUpdateAt())
                .build();
    }


}
