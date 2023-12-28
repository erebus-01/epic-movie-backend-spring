package com.programing.rating.service;

import com.programing.rating.dto.RatingLevelRequest;
import com.programing.rating.dto.RatingRequest;
import com.programing.rating.dto.RatingResponse;

import java.util.List;
import java.util.UUID;

public interface RatingService {
    String rateMovie(RatingRequest ratingRequest);
    RatingResponse updateRatingLevel(Long id, RatingLevelRequest ratingRequest);

    RatingResponse getRatingById(Long ratingId);

    List<RatingResponse> getRatingsByUserProfile(Long userProfileId);

    List<RatingResponse> getRatingsByMovie(UUID movieId);

    String deleteRating(Long ratingId);
    String deleteAllByMovieId(UUID movieId);

    String deleteAllByUserProfile(Long userProfileId);
    boolean hasUserRatedMovie(Long userProfileId, UUID movieId);

}
