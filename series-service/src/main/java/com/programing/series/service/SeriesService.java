package com.programing.series.service;

import com.programing.series.config.FeignClientCrew;
import com.programing.series.config.FeignClientEpisodes;
import com.programing.series.config.FeignClientGenre;
import com.programing.series.dto.*;
import com.programing.series.impl.SeriesImpl;
import com.programing.series.model.*;
import com.programing.series.repository.SeriesRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class SeriesService implements SeriesImpl {

    private final SeriesRepository repository;
    private final FeignClientEpisodes clientEpisodes;
    private final FeignClientGenre clientGenre;
    private final FeignClientCrew clientCrew;

    @Override
    public SeriesResponse save(SeriesRequest request) {
        Series series = Series.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .image(request.getImage())
                .backdrop(request.getBackdrop())
                .logo(request.getLogo())
                .age(request.getAge())
                .year(request.getYear())
                .nation(request.getNation())
                .releaseDate(request.getReleaseDate())
                .trailerURL(request.getTrailerURL())
                .url(request.getUrl())
                .released(request.isReleased())
                .actors(request.getActors())
                .directors(request.getDirectors())
                .episodes(request.getEpisodes())
                .genres(request.getGenres())
                .build();

        series = repository.save(series);

        List<SeasonRequest> seasonRequests = convertSeasons(request.getSeasons());
        List<Season> seasons = createSeasons(seasonRequests, series);

        series.setSeasons(seasons);

        repository.save(series);

        return maptoSeriesResponse(series);

    }

    @Override
    public SeriesResponse update(SeriesRequest request, UUID id) {
        Optional<Series> seriesOptional = repository.findById(id);
        if (seriesOptional.isPresent()) {
            Series series = Series.builder()
                    .title(request.getTitle())
                    .description(request.getDescription())
                    .image(request.getImage())
                    .backdrop(request.getBackdrop())
                    .logo(request.getLogo())
                    .age(request.getAge())
                    .year(request.getYear())
                    .nation(request.getNation())
                    .seasons(request.getSeasons())
                    .releaseDate(request.getReleaseDate())
                    .trailerURL(request.getTrailerURL())
                    .url(request.getUrl())
                    .actors(request.getActors())
                    .directors(request.getDirectors())
                    .released(request.isReleased())
                    .genres(request.getGenres())
                    .build();

            repository.save(series);

            return maptoSeriesResponse(series);
        }
        return null;
    }

    @Override
    public SeriesResponse getOne(UUID id) {
        Series series = repository.findById(id).orElseThrow();

        return maptoSeriesResponse(series);
    }

    @Override
    public List<SeriesResponse> getAll() {
        List<Series> series = repository.findAll();
        return series.stream().map(this::maptoSeriesResponse).collect(Collectors.toList());
    }

    @Override
    public String delete(UUID id) {
        Optional<Series> series = repository.findById(id);
        if (series.isPresent()) {
            repository.deleteById(id);
            return "Delete series with id " + id + " successfully.";
        }
        return null;
    }

    private SeriesResponse maptoSeriesResponse(Series request) {

        log.info("Genre mapper: {}", convertToGenreMapper(request.getGenres()));

        return SeriesResponse.builder()
                .id(request.getId())
                .title(request.getTitle())
                .description(request.getDescription())
                .image(request.getImage())
                .backdrop(request.getBackdrop())
                .logo(request.getLogo())
                .age(request.getAge())
                .year(request.getYear())
                .nation(request.getNation())
                .seasons(convertSeasonsToDTO(request.getSeasons()))
                .releaseDate(request.getReleaseDate())
                .trailerURL(request.getTrailerURL())
                .url(request.getUrl())
                .released(request.isReleased())
                .genres(request.getGenres())
                .episodes(request.getEpisodes())
                .actors(request.getActors())
                .actorsMapper(request.getActors() != null && !request.getActors().isEmpty() ? convertToCrewMapper(request.getActors()) : null)
                .directors(request.getDirectors())
                .directorsMapper(request.getDirectors() != null && !request.getDirectors().isEmpty() ? convertToCrewMapper(request.getDirectors()) : null)
                .genreMapper(request.getGenres() != null && !request.getGenres().isEmpty() ? convertToGenreMapper(request.getGenres()) : null)
                .createAt(request.getCreateAt())
                .updateAt(request.getUpdateAt())
                .build();
    }


    private List<SeasonRequest> convertSeasons(List<Season> seasons) {
        return seasons.stream()
                .map(season -> SeasonRequest.builder()
                        .seasonNumber(season.getSeasonNumber())
                        .episodeIds(season.getEpisodeIds())
                        .build())
                .collect(Collectors.toList());
    }

    private List<Season> createSeasons(List<SeasonRequest> seasonRequests, Series series) {
        return seasonRequests.stream()
                .map(seasonRequest -> Season.builder()
                        .seasonNumber(seasonRequest.getSeasonNumber())
                        .series(series)
                        .build())
                .collect(Collectors.toList());
    }

    private List<Season> convertSeasonsToDTO(List<Season> seasons) {
        return seasons.stream()
                .map(season -> {
//                    List<EpisodesMapper> episodesMappers = season.getEpisodeIds().stream()
//                            .map(clientEpisodes::getOne)
//                            .toList();

                    return Season.builder()
                            .id(season.getId())
                            .series(season.getSeries())
                            .seasonNumber(season.getSeasonNumber())
                            .episodeIds(season.getEpisodeIds())
                            .createAt(season.getCreateAt())
                            .updateAt(season.getUpdateAt())
                            .build();
                })
                .collect(Collectors.toList());
    }

    private List<GenreMapper> convertToGenreMapper(List<Long> genreIDs) {
        return genreIDs.stream().map(clientGenre::getGenreById).toList();
    }

    private List<CrewMapper> convertToCrewMapper(List<UUID> genreIDs) {
        return genreIDs.stream().map(clientCrew::findCrewById).toList();
    }

}
