package com.programing.movie.service;

import com.programing.movie.config.FeignClientCrew;
import com.programing.movie.config.FeignClientGenre;
import com.programing.movie.dto.MovieRequest;
import com.programing.movie.dto.MovieResponse;
import com.programing.movie.impl.MovieImpl;
import com.programing.movie.model.CrewMapper;
import com.programing.movie.model.GenreMapper;
import com.programing.movie.model.Movie;
import com.programing.movie.repository.MovieRepository;
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
public class MovieService implements MovieImpl {

    private final MovieRepository repository;
    private final FeignClientCrew crewClient;
    private final FeignClientGenre clientGenre;
    private final WebClient.Builder webClientConfig;

    @Override
    public MovieResponse save(MovieRequest request) {
        Movie movie = Movie.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .image(request.getImage())
                .backdrop(request.getBackdrop())
                .age(request.getAge())
                .durations(request.getDurations())
                .releaseDate(request.getReleaseDate())
                .trailerURL(request.getTrailerURL())
                .url(request.getUrl())
                .released(request.isReleased())
                .crews(request.getCrews())
                .genres(request.getGenres())
                .build();

        repository.save(movie);

        log.info("Save movie {} successfully", movie);

        return mapToMovieResponse(movie);
    }

    @Override
    public List<MovieResponse> saveAll(List<MovieRequest> requests) {
        List<Movie> movies = new ArrayList<>();

        for (MovieRequest request : requests) {
            Movie movie = Movie.builder()
                    .title(request.getTitle())
                    .description(request.getDescription())
                    .image(request.getImage())
                    .backdrop(request.getBackdrop())
                    .age(request.getAge())
                    .durations(request.getDurations())
                    .releaseDate(request.getReleaseDate())
                    .trailerURL(request.getTrailerURL())
                    .url(request.getUrl())
                    .released(request.isReleased())
                    .crews(request.getCrews())
                    .genres(request.getGenres())
                    .build();

            movies.add(movie);
        }

        repository.saveAll(movies);

        log.info("Saved {} movies successfully", movies.size());

        return movies.stream()
                .map(this::mapToMovieResponse)
                .collect(Collectors.toList());
    }


    @Override
    public MovieResponse updateById(MovieRequest request, UUID id) {

        Optional<Movie> movieOptional = repository.findById(id);

        if(movieOptional.isPresent()) {
            return MovieBuilder(request);
        }

        return null;
    }

    @Override
    public List<MovieResponse> getAllMovie() {
        List<Movie> movies = repository.findAll();
        return movies.stream().map(this::mapToMovieResponse).collect(Collectors.toList());
    }

    @Override
    public MovieResponse findMovieById(UUID id) {
        Movie movie = repository.findById(id).orElseThrow();

        List<CrewMapper> crewMapperList = movie.getCrews().stream().map(crewClient::findCrewById).toList();
        List<GenreMapper> genreMapperList = movie.getGenres().stream().map(clientGenre::getGenreById).toList();

        movie.setCrewsMapper(crewMapperList);
        movie.setGenreMapper(genreMapperList);

        log.info("My crew: {}", crewMapperList);

        return mapToMovieResponse(movie);
    }
    @Override
    public String deleteById(UUID id) {
        Optional<Movie> movie = repository.findById(id);
        if(movie.isPresent()) {
            repository.deleteById(id);
            log.info("Delete movie with id {} successfully", id);
            return "Delete movie with id " + id + " successfully";
        }
        return null;
    }

    private MovieResponse mapToMovieResponse(Movie request) {
        return MovieResponse.builder()
                .id(request.getId())
                .title(request.getTitle())
                .description(request.getDescription())
                .image(request.getImage())
                .backdrop(request.getBackdrop())
                .age(request.getAge())
                .nation(request.getNation())
                .durations(request.getDurations())
                .releaseDate(request.getReleaseDate())
                .trailerURL(request.getTrailerURL())
                .url(request.getUrl())
                .released(request.isReleased())
                .createAt(request.getCreateAt())
                .crews(request.getCrews())
                .crewsMapper(request.getCrewsMapper())
                .genres(request.getGenres())
                .genreMappers(request.getGenreMapper())
                .updateAt(request.getUpdateAt())
                .build();
    }


    private MovieResponse MovieBuilder(MovieRequest request) {
        Movie movie = Movie.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .image(request.getImage())
                .backdrop(request.getBackdrop())
                .age(request.getAge())
                .nation(request.getNation())
                .durations(request.getDurations())
                .releaseDate(request.getReleaseDate())
                .trailerURL(request.getTrailerURL())
                .url(request.getUrl())
                .crews(request.getCrews())
                .genres(request.getGenres())
                .released(request.isReleased())
                .build();

        repository.save(movie);

        log.info("Save movie {} successfully", movie);

        return mapToMovieResponse(movie);
    }


}
