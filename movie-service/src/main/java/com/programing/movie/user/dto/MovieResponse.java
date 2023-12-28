package com.programing.movie.user.dto;

import com.programing.movie.user.model.CrewMapper;
import com.programing.movie.user.model.GenreMapper;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovieResponse {

    private UUID id;
    private String title;
    private String description;
    private String image;
    private String backdrop;
    private Integer age;
    private String nation;
    private Integer durations;
    private Date releaseDate;
    private String trailerURL;
    private String url;
    private boolean released;
    private List<UUID> crews;
    private List<Long> genres;
    private List<GenreMapper> genreMappers;
    private List<CrewMapper> crewsMapper;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;


}
