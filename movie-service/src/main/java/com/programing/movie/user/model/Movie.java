package com.programing.movie.user.model;

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
@Table(name = "movie")
public class Movie {

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

    @Column(name = "age")
    private Integer age;

    @Column(name = "nation")
    private String nation;

    @Column(name = "durations")
    private Integer durations;

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
            name = "crew_movie",
            joinColumns = @JoinColumn(name = "movie_id")
    )
    @Column(name = "uuid_crew")
    private List<UUID> crews;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "genre_movie",
            joinColumns = @JoinColumn(name = "movie_id")
    )
    @Column(name = "genre_id")
    private List<Long> genres;

    @Transient
    private List<CrewMapper> crewsMapper;

    @Transient
    private List<GenreMapper> genreMapper;

    @CreationTimestamp
    private LocalDateTime createAt;
    @UpdateTimestamp
    private LocalDateTime updateAt;



}
