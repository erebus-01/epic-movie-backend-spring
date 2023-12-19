package com.programing.genre.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;
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
    private Integer durations;
    private Date releaseDate;
    private String trailerURL;
    private String url;
    private boolean released;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;


}
