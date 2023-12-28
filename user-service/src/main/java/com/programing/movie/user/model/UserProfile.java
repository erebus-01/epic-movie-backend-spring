package com.programing.movie.user.model;

import jakarta.persistence.Entity;
import lombok.*;

import java.util.UUID;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserProfile {
    private UUID profileId;
    private String name;
    private String image;
    private Boolean locked;
}
