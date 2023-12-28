package com.programing.movie.user.profile.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserProfileResponse {
    private Long id;
    private Set<UUID> userId;
    private String profileName;
    private String email;
    private String imageAvt;
    private boolean locked;
    private List<Long> watchedMovieIds;
    private List<Long> searchMovieIds;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
