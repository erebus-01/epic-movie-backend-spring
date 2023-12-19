package com.programing.episode.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "episode")
public class Episode {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private Integer number;
    private String title;
    private String description;
    private String imageUrl;
    private Integer durations;
    @Temporal(TemporalType.DATE)
    private Date releasedDate;
    private String url;

    @ElementCollection
    @CollectionTable(name = "episode_season", joinColumns = @JoinColumn(name = "episode_id"))
    @Column(name = "season_id")
    private Set<Long> seasonId = new HashSet<>(1);

    @ElementCollection
    @CollectionTable(name = "episode_series", joinColumns = @JoinColumn(name = "episode_id"))
    @Column(name = "series_id")
    private Set<UUID> seriesId = new HashSet<>(1);

    @CreationTimestamp
    private LocalDateTime createAt;
    @UpdateTimestamp
    private LocalDateTime updateAt;


}
