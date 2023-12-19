package com.programing.crew.model;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieMapper {

    private UUID id;
    private String title;
    private String description;
    private String image;
    private String backdrop;
    private Integer age;
    private String nation;
    private Integer durations;
    private String trailerURL;
    private String url;
    @Temporal(TemporalType.DATE)
    private Date releaseDate;
    private boolean released;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;

}
