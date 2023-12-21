package com.programing.series.dto;

import com.programing.series.model.CrewMapper;
import com.programing.series.model.GenreMapper;
import com.programing.series.model.Season;
import jakarta.persistence.*;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SeriesResponse {

    private UUID id;
    private String title;
    private String description;
    private String image;
    private String backdrop;
    private String logo;
    private Integer age;
    private Integer year;
    private String nation;
    private List<Season> seasons;
    private Date releaseDate;
    private String trailerURL;
    private String url;
    private boolean released;
    private List<UUID> actors;
    private List<UUID> directors;
    private List<Long> genres;
    private List<UUID> episodes;
    private List<CrewMapper> actorsMapper;
    private List<CrewMapper> directorsMapper;
    private List<GenreMapper> genreMapper;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;


}
