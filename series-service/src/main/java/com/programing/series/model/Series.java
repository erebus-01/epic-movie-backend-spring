package com.programing.series.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotNull;
import lombok.*;
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
@Entity
@ToString
@Table(name = "series")
public class Series {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;


    @Column(name = "title")
    @NotNull
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "image")
    @NotNull
    private String image;

    @Column(name = "backdrop")
    @NotNull
    private String backdrop;

    @Column(name = "logo")
    private String logo;

    @Column(name = "age")
    private Integer age;

    @Column(name = "year")
    private Integer year;

    @Column(name = "nation")
    private String nation;



    @OneToMany(mappedBy = "series", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Season> seasons;


    @Column(name = "release_date")
    @NotNull
    @Temporal(TemporalType.DATE)
    private Date releaseDate;

    @Column(name = "trailer")
    private String trailerURL;

    @Column(name = "url")
    private String url;

    @Column(name = "released")
    @AssertTrue
    private boolean released;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "crew_actor",
            joinColumns = @JoinColumn(name = "series_id")
    )
    @Column(name = "uuid_actor")

    private List<UUID> actors;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "crew_director",
            joinColumns = @JoinColumn(name = "series_id")
    )
    @Column(name = "uuid_director")
    private List<UUID>directors;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "genre_series",
            joinColumns = @JoinColumn(name = "series_id")
    )
    @Column(name = "genre_id")
    private List<Long> genres;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "episode_series",
            joinColumns = @JoinColumn(name = "series_id")
    )
    @Column(name = "episode_id")
    private List<UUID> episodes;

    @Transient
    private List<CrewMapper> actorsMapper;

    @Transient
    private List<CrewMapper> directorsMapper;

    @Transient
    private List<GenreMapper> genreMapper;

    @CreationTimestamp
    private LocalDateTime createAt;
    @UpdateTimestamp
    private LocalDateTime updateAt;




}
