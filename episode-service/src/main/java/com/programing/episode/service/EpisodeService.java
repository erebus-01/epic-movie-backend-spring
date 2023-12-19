package com.programing.episode.service;

import com.programing.episode.dto.EpisodeRequest;
import com.programing.episode.dto.EpisodeResponse;
import com.programing.episode.impl.EpisodeImpl;
import com.programing.episode.model.Episode;
import com.programing.episode.repository.EpisodeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class EpisodeService implements EpisodeImpl {

    private final EpisodeRepository repository;

    @Override
    public EpisodeResponse save(EpisodeRequest request) {
        Episode episode = Episode.builder()
                .number(request.getNumber())
                .title(request.getTitle())
                .description(request.getDescription())
                .seasonId(request.getSeasonIds())
                .imageUrl(request.getImageUrl())
                .durations(request.getDurations())
                .releasedDate(request.getReleasedDate())
                .seriesId(request.getSeriesIds())
                .url(request.getUrl())
                .build();

        Episode savedEpisode = repository.save(episode);

        log.info("Saved episode with ID: {}", savedEpisode.getId());

        return mapToEpisodeResponse(savedEpisode);

    }

    @Override
    public EpisodeResponse update(EpisodeRequest request, UUID id) {
        Episode episode = repository.findById(id).orElseThrow();

        episode.setNumber(request.getNumber());
        episode.setTitle(request.getTitle());
        episode.setDescription(request.getDescription());
        episode.setSeasonId(request.getSeasonIds());
        episode.setImageUrl(request.getImageUrl());
        episode.setDurations(request.getDurations());
        episode.setReleasedDate(request.getReleasedDate());
        episode.setUrl(request.getUrl());

        repository.save(episode);

        return mapToEpisodeResponse(episode);
    }

    @Override
    public EpisodeResponse getOne(UUID id) {
        Episode episode = repository.findById(id).orElseThrow();
        return mapToEpisodeResponse(episode);
    }

    @Override
    public List<EpisodeResponse> getAllMovieBySeasonId(Long seasonId) {
        List<Episode> episodes = repository.findAllBySeasonId(seasonId);

        return episodes.stream()
                .map(this::mapToEpisodeResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<EpisodeResponse> getEpisodesBySeriesId(UUID seriesID) {
        List<Episode> episodes = repository.findAllBySeriesId(seriesID, Sort.by(Sort.Direction.ASC, "seasonId", "number"));

        return episodes.stream()
                .map(this::mapToEpisodeResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<EpisodeResponse> saveAll(List<EpisodeRequest> requests) {
        List<Episode> episodes = requests.stream()
                .map(request -> Episode.builder()
                        .number(request.getNumber())
                        .title(request.getTitle())
                        .description(request.getDescription())
                        .imageUrl(request.getImageUrl())
                        .durations(request.getDurations())
                        .seasonId(request.getSeasonIds())
                        .seriesId(request.getSeriesIds())
                        .releasedDate(request.getReleasedDate())
                        .url(request.getUrl())
                        .build())
                .collect(Collectors.toList());

        List<Episode> savedEpisodes = repository.saveAll(episodes);

        log.info("Saved {} episodes", savedEpisodes.size());

        return savedEpisodes.stream()
                .map(this::mapToEpisodeResponse)
                .collect(Collectors.toList());
    }

    @Override
    public String delete(UUID id) {
        Episode episode = repository.findById(id).orElseThrow();
        repository.deleteById(id);
        return "Delete episode with id " + id + " successfully";
    }

    @Override
    public List<EpisodeResponse> getEpisodesBySeriesAndSeason(UUID seriesId, Long seasonId) {
        List<Episode> episodes = repository.findBySeriesIdAndSeasonId(seriesId, seasonId, Sort.by(Sort.Direction.ASC, "number"));

        return episodes.stream()
                .map(this::mapToEpisodeResponse)
                .collect(Collectors.toList());
    }


    private EpisodeResponse mapToEpisodeResponse(Episode request) {
        return EpisodeResponse.builder()
                .id(request.getId())
                .number(request.getNumber())
                .title(request.getTitle())
                .description(request.getDescription())
                .imageUrl(request.getImageUrl())
                .durations(request.getDurations())
                .seasonIds(request.getSeasonId())
                .seriesIds(request.getSeriesId())
                .releasedDate(request.getReleasedDate())
                .url(request.getUrl())
                .createAt(request.getCreateAt())
                .updateAt(request.getUpdateAt())
                .build();
    }

}
