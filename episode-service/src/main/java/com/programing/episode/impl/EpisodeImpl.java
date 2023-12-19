package com.programing.episode.impl;

import com.programing.episode.dto.EpisodeRequest;
import com.programing.episode.dto.EpisodeResponse;

import java.util.List;
import java.util.UUID;

public interface EpisodeImpl {

    EpisodeResponse save(EpisodeRequest request);
    EpisodeResponse update(EpisodeRequest request, UUID id);
    EpisodeResponse getOne(UUID id);
    List<EpisodeResponse> getAllMovieBySeasonId(Long id);
    List<EpisodeResponse> getEpisodesBySeriesId(UUID seriesID);
    List<EpisodeResponse> saveAll(List<EpisodeRequest> requests);
    String delete(UUID id);
    List<EpisodeResponse> getEpisodesBySeriesAndSeason(UUID seriesId, Long seasonId);


}
