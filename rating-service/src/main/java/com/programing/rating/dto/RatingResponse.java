package com.programing.rating.dto;

import com.programing.rating.model.RatingLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RatingResponse {
    private Long id;
    private Long userProfile;
    private RatingLevel ratingLevel;
    private UUID movie;
    private LocalDateTime createdAt;
}
