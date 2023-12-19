package com.programing.genre.service;

import com.programing.genre.config.FeignClientMovie;
import com.programing.genre.dto.GenreRequest;
import com.programing.genre.dto.GenreResponse;
import com.programing.genre.impl.GenreImpl;
import com.programing.genre.model.Genre;
import com.programing.genre.model.MovieMapper;
import com.programing.genre.repository.GenreRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class GenreService implements GenreImpl {

    private final GenreRepository repository;
    private final FeignClientMovie feignClientMovie;

    @Override
    public GenreResponse save(GenreRequest request) {
        Genre genre = Genre.builder()
                .name(request.getName())
                .description(request.getDescription())
                .movies(request.getMovies())
                .build();

        repository.save(genre);

        return maptoGenreResponse(genre);
    }

    @Override
    public GenreResponse getGenreById(Long id) {
        Optional<Genre> genreOptional = repository.findById(id);
        if(genreOptional.isPresent()) {
            Genre genre = genreOptional.get();
            return maptoGenreResponse(genre);
        }
         return null;
    }

    @Override
    public List<GenreResponse> saveListGenre(List<GenreRequest> request) {

        List<GenreResponse> genreResponseList = new ArrayList<>();

        if(!request.isEmpty()) {
            for(GenreRequest genreRequest : request) {
                Genre genre = Genre.builder()
                        .name(genreRequest.getName())
                        .description(genreRequest.getDescription())
                        .build();

                repository.save(genre);

                genreResponseList.add(maptoGenreResponse(genre));
            }
        }

        return genreResponseList;
    }

    @Override
    public List<MovieMapper> getById(Long id) {
        Optional<Genre> genreOptional = repository.findById(id);

        if(genreOptional.isPresent()) {
            Genre genre = genreOptional.get();

            List<MovieMapper> movieMapperList = genre.getMovies().stream().map(feignClientMovie::findMovieById).toList();

            return movieMapperList;
        }

        return null;
    }

    @Override
    public GenreResponse update(GenreRequest request, Long id) {

        Optional<Genre> genreOptional = repository.findById(id);
        if(genreOptional.isPresent()) {
            Genre genre = genreOptional.get();
            genre.setName(request.getName());
            genre.setDescription(request.getDescription());

            repository.save(genre);

            return maptoGenreResponse(genre);
        }

        return null;
    }

    @Override
    public List<GenreResponse> getAll() {
        List<Genre> genreList = repository.findAll();
        return genreList.stream().map(this::maptoGenreResponse).collect(Collectors.toList());
    }

    @Override
    public String deleteById(Long id) {
        Optional<Genre> genre = repository.findById(id);
        if(genre.isPresent()) {
            repository.deleteById(id);
            return "Delete genre with id " + id + "successfully";
        }
        return "Can't delete genre";
    }

    private GenreResponse maptoGenreResponse(Genre request) {
        return GenreResponse.builder()
                .id(request.getId())
                .name(request.getName())
                .description(request.getDescription())
                .movies(request.getMovies())
                .createAt(request.getCreateAt())
                .updateAt(request.getUpdateAt())
                .build();
    }

}
