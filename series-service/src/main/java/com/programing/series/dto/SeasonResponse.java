package com.programing.series.dto;

import com.programing.series.model.EpisodesMapper;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SeasonResponse {

    private Long id;
    private Integer seasonNumber;
    private List<UUID> episodeIds;
    private List<EpisodesMapper> episodesMappers;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
}
