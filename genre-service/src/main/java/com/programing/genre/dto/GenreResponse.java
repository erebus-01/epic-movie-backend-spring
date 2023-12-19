package com.programing.genre.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GenreResponse {

    private Long id;
    private String name;
    private String description;
    private List<UUID> movies;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;

}
