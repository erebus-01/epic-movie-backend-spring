package com.programing.movie.user.profile.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserProfileRequest {
    private Set<UUID> userId;
    private String profileName;
    private String imageAvt;
    private String email;
    private boolean locked;
    private List<Long> watchedMovieIds;
    private List<Long> searchMovieIds;
}
