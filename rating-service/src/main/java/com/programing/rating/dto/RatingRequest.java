package com.programing.rating.dto;

import com.programing.rating.model.RatingLevel;
import lombok.*;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RatingRequest {
    private Long userProfile;
    private RatingLevel ratingLevel;
    private UUID movie;
}
