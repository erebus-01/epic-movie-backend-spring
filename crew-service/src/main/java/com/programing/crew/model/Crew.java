package com.programing.crew.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "crew")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Crew {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name="firstname")
    @NotNull
    private String firstname;

    @Column(name="lastname")
    @NotNull
    private String lastname;

    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;

    private String nation;
    private String description;
    private String image;

    @Enumerated(EnumType.ORDINAL)
    private TypeCrew typeCrew;

    @Enumerated(EnumType.STRING)
    private Gender gender;


    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "crew_movie",
            joinColumns = @JoinColumn(name = "uuid_crew")
    )
    @Column(name = "movie_id")
    private List<UUID> movies = new ArrayList<>();


    @Transient
    private List<MovieMapper> movieMapper;


    @CreationTimestamp
    private LocalDateTime createAt;
    @UpdateTimestamp
    private LocalDateTime updateAt;
}
