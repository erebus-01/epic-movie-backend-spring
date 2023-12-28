package com.programing.rating.dto;

import com.programing.rating.model.RatingLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RatingLevelRequest {
    private RatingLevel ratingLevel;
}
