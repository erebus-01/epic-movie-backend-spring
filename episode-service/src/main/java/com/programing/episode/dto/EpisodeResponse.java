package com.programing.episode.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EpisodeResponse {
    private UUID id;
    private Integer number;
    private String title;
    private String description;
    private String imageUrl;
    private Integer durations;
    private Set<Long> seasonIds;
    private Set<UUID> seriesIds;
    private Date releasedDate;
    private String url;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
}
