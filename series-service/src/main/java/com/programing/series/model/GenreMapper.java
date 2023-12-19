package com.programing.series.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class GenreMapper {


    private Long id;
    private String name;
    private String description;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;


}
