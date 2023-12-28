package com.programing.rating.impl;

import com.programing.rating.dto.RatingLevelRequest;
import com.programing.rating.dto.RatingRequest;
import com.programing.rating.dto.RatingResponse;
import com.programing.rating.model.Rating;
import com.programing.rating.repository.RatingRepository;
import com.programing.rating.service.RatingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class RatingServiceImpl implements RatingService {
    private final RatingRepository repository;
    @Override
    public String rateMovie(RatingRequest ratingRequest) {

        List<Rating> existingRatings = repository.findByUserProfileAndMovie(ratingRequest.getUserProfile(), ratingRequest.getMovie());

        if(existingRatings.isEmpty()) {

            Rating newRating = Rating.builder()
                    .userProfile(ratingRequest.getUserProfile())
                    .ratingLevel(ratingRequest.getRatingLevel())
                    .movie(ratingRequest.getMovie())
                    .build();
            repository.save(newRating);

            return "rating movie successfully";
        }

        Rating existingRating = existingRatings.get(0);
        existingRating.setRatingLevel(ratingRequest.getRatingLevel());
        repository.save(existingRating);

        return "Updated rating for the movie successfully";
    }

    @Override
    @Transactional
    public RatingResponse updateRatingLevel(Long id, RatingLevelRequest ratingRequest) {
        Optional<Rating> optionalRating = repository.findById(id);

        if (optionalRating.isPresent()) {
            Rating rating = optionalRating.get();
            rating.setRatingLevel(ratingRequest.getRatingLevel());
            Rating updatedRating = repository.save(rating);
            return mapToRatingResponse(updatedRating);
        } else {
            throw new RuntimeException("Rating not found with id: " + id);
        }
    }

    @Override
    public RatingResponse getRatingById(Long ratingId) {
        Rating rating = repository.findById(ratingId).orElseThrow(null);

        return mapToRatingResponse(rating);
    }

    @Override
    public List<RatingResponse> getRatingsByUserProfile(Long userProfileId) {
        List<Rating> ratings = repository.findByUserProfile(userProfileId);
        return ratings.stream().map(this::mapToRatingResponse).collect(Collectors.toList());

    }

    @Override
    public List<RatingResponse> getRatingsByMovie(UUID movieId) {
        List<Rating> ratings = repository.findByMovie(movieId);
        return ratings.stream().map(this::mapToRatingResponse).collect(Collectors.toList());

    }

    @Override
    public String deleteRating(Long ratingId) {
        Optional<Rating> optionalRating = repository.findById(ratingId);

        if (optionalRating.isPresent()) {
            repository.delete(optionalRating.get());
            return "Delete rating successfully";
        } else {
            return ("Rating not found with id: " + ratingId);
        }
    }

    @Override
    public String deleteAllByMovieId(UUID movieId) {
        List<Rating> ratings = repository.findByMovie(movieId);

        if (ratings.isEmpty()) {
            repository.deleteByMovie(movieId);
            return "All ratings for the movie with ID " + movieId + " deleted successfully.";
        } else {
            return "No ratings found for the movie rewith ID " + movieId + ".";
        }
    }

    @Override
    public String deleteAllByUserProfile(Long userProfileId) {
        List<Rating> ratings = repository.findByUserProfile(userProfileId);

        if (ratings.isEmpty()) {
            repository.deleteByUserProfile(userProfileId);
            return "All ratings for the user with ID " + userProfileId + " deleted successfully.";
        } else {
            return "No ratings found for the user with ID " + userProfileId + ".";
        }
    }

    @Override
    public boolean hasUserRatedMovie(Long userProfile, UUID movie) {
        List<Rating> userRatingsForMovie = repository.findByUserProfileAndMovie(userProfile, movie);
        return userRatingsForMovie.isEmpty();
    }

    private RatingResponse mapToRatingResponse(Rating rating) {
        return RatingResponse.builder()
                .id(rating.getId())
                .userProfile(rating.getUserProfile())
                .ratingLevel(rating.getRatingLevel())
                .movie(rating.getMovie())
                .createdAt(rating.getCreateAt())
                .build();
    }
}
