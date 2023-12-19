package com.programing.series.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "season")
public class Season {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @JoinColumn(name = "series_id")
    @JsonIgnoreProperties("seasons")
    private Series series;


    @Column(name = "season_number")
    private Integer seasonNumber;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "episode_season",
            joinColumns = @JoinColumn(name = "season_id")
    )
    @Column(name = "episode_id")
    private List<UUID> episodeIds;

    @Transient
    private List<EpisodesMapper> episodesMappers;

    @CreationTimestamp
    private LocalDateTime createAt;
    @UpdateTimestamp
    private LocalDateTime updateAt;


}
