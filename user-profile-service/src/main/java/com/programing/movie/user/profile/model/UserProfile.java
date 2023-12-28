package com.programing.movie.user.profile.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.AssertFalse;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "user_profile")
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String profileName;
    private String email;
    private String password;
    private String imageAvt;

    @ElementCollection
    @CollectionTable(name = "user_system_profile", joinColumns = @JoinColumn(name = "profile_id"))
    @Column(name = "user_id")
    private Set<UUID> userId = new HashSet<>(1);

    private boolean locked;

    @ElementCollection
    @CollectionTable(name = "user_system_watched", joinColumns = @JoinColumn(name = "user_profile_id"))
    @Column(name = "movie_id")
    private List<Long> watchedMovieIds;

    @ElementCollection
    @CollectionTable(name = "user_system_search", joinColumns = @JoinColumn(name = "user_profile_id"))
    @Column(name = "search_id")
    private List<Long> searchMovieIds;


    @CreationTimestamp
    private LocalDateTime createAt;
    @UpdateTimestamp
    private LocalDateTime updateAt;


}
