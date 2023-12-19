package com.programing.series.service;

import com.programing.series.config.FeignClientEpisodes;
import com.programing.series.dto.*;
import com.programing.series.impl.SeriesImpl;
import com.programing.series.model.EpisodesMapper;
import com.programing.series.model.Season;
import com.programing.series.model.Series;
import com.programing.series.repository.SeriesRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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

    @Override
    public SeriesResponse save(SeriesRequest request) {
        Series series = Series.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .image(request.getImage())
                .backdrop(request.getBackdrop())
                .age(request.getAge())
                .nation(request.getNation())
                .releaseDate(request.getReleaseDate())
                .trailerURL(request.getTrailerURL())
                .url(request.getUrl())
                .released(request.isReleased())
                .crews(request.getCrews())
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
                    .age(request.getAge())
                    .nation(request.getNation())
                    .seasons(request.getSeasons())
                    .releaseDate(request.getReleaseDate())
                    .trailerURL(request.getTrailerURL())
                    .url(request.getUrl())
                    .released(request.isReleased())
                    .crews(request.getCrews())
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
        return SeriesResponse.builder()
                .id(request.getId())
                .title(request.getTitle())
                .description(request.getDescription())
                .image(request.getImage())
                .backdrop(request.getBackdrop())
                .age(request.getAge())
                .nation(request.getNation())
                .seasons(convertSeasonsToDTO(request.getSeasons()))
                .releaseDate(request.getReleaseDate())
                .trailerURL(request.getTrailerURL())
                .url(request.getUrl())
                .released(request.isReleased())
                .crews(request.getCrews())
                .genres(request.getGenres())
                .episodes(request.getEpisodes())
                .crewsMapper(request.getCrewsMapper())
                .genreMapper(request.getGenreMapper())
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
                        .episodeIds(seasonRequest.getEpisodeIds())
                        .series(series)
                        .build())
                .collect(Collectors.toList());
    }

    private List<Season> convertSeasonsToDTO(List<Season> seasons) {
        return seasons.stream()
                .map(season -> {
                    List<EpisodesMapper> episodesMappers = season.getEpisodeIds().stream()
                            .map(clientEpisodes::getOne)
                            .toList();

                    return Season.builder()
                            .id(season.getId())
                            .seasonNumber(season.getSeasonNumber())
                            .episodeIds(season.getEpisodeIds())
                            .episodesMappers(episodesMappers)
                            .createAt(season.getCreateAt())
                            .updateAt(season.getUpdateAt())
                            .build();
                })
                .collect(Collectors.toList());
    }


}
