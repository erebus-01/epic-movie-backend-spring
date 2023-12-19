package com.programing.episode.repository;

import com.programing.episode.dto.EpisodeResponse;
import com.programing.episode.model.Episode;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface EpisodeRepository extends JpaRepository<Episode, UUID> {
    List<Episode> findAllBySeasonId(Long seasonId);
    List<Episode> findAllBySeriesId(UUID seasonId, Sort sort);
    List<Episode> findBySeriesIdAndSeasonId(UUID seriesId, Long seasonId, Sort sort);


}
