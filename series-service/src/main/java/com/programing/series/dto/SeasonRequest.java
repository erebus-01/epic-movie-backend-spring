package com.programing.series.dto;

import com.programing.series.model.Series;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SeasonRequest {

    private Series series;
    private Integer seasonNumber;
    private List<UUID> episodeIds;

}
