package com.programing.movie.dto;

import com.programing.movie.model.CrewMapper;
import jakarta.persistence.*;
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
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovieRequest {

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
    private List<UUID> crews;
    private List<Long> genres;
    private boolean released;

}
