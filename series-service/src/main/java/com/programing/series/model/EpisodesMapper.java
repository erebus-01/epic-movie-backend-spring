package com.programing.series.model;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EpisodesMapper {

    private UUID id;
    private Integer number;
    private String title;
    private String description;
    private String imageUrl;
    private Integer durations;
    @Temporal(TemporalType.DATE)
    private Date releasedDate;
    private String url;

}
