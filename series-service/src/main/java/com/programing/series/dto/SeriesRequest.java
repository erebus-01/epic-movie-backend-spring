package com.programing.series.dto;


import com.programing.series.model.CrewMapper;
import com.programing.series.model.GenreMapper;
import com.programing.series.model.Season;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SeriesRequest {


    private String title;
    private String description;
    private String image;
    private String backdrop;
    private Integer age;
    private String nation;
    private Date releaseDate;
    private String trailerURL;
    private String url;
    private boolean released;
    private List<UUID> crews;
    private List<Long> genres;
    private List<UUID> episodes;
    private List<Season> seasons;
    private List<CrewMapper> crewsMapper;
    private List<GenreMapper> genreMapper;




}
