package com.programing.rating.controller;

import com.programing.rating.dto.RatingLevelRequest;
import com.programing.rating.dto.RatingRequest;
import com.programing.rating.dto.RatingResponse;
import com.programing.rating.impl.RatingServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/rating")
@RequiredArgsConstructor
public class RatingController {

    private final RatingServiceImpl ratingService;

    @PostMapping
    public ResponseEntity<String> rateMovie(@RequestBody RatingRequest ratingRequest) {
        String response = ratingService.rateMovie(ratingRequest);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}/update-rating-level")
    public ResponseEntity<RatingResponse> updateRatingLevel(
            @PathVariable("id") Long ratingId,
            @RequestBody RatingLevelRequest newRatingLevelRequest) {
        RatingResponse updatedRating = ratingService.updateRatingLevel(ratingId, newRatingLevelRequest);
        return ResponseEntity.ok(updatedRating);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RatingResponse> getRatingById(@PathVariable("id") Long ratingId) {
        RatingResponse response = ratingService.getRatingById(ratingId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<RatingResponse>> getRatingsByUserProfile(@PathVariable("userId") Long userProfileId) {
        List<RatingResponse> response = ratingService.getRatingsByUserProfile(userProfileId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/movie/{movieId}")
    public ResponseEntity<List<RatingResponse>> getRatingsByMovie(@PathVariable("movieId") UUID movieId) {
        List<RatingResponse> response = ratingService.getRatingsByMovie(movieId);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRating(@PathVariable("id") Long ratingId) {
        String result = ratingService.deleteRating(ratingId);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/deleteByUserProfile/{userProfileId}")
    public ResponseEntity<String> deleteAllByUserProfile(@PathVariable("userProfileId") Long userProfileId) {
        String result = ratingService.deleteAllByUserProfile(userProfileId);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/deleteByMovie/{movieId}")
    public ResponseEntity<String> deleteAllByMovieId(@PathVariable("movieId") UUID movieId) {
        String result = ratingService.deleteAllByMovieId(movieId);
        return ResponseEntity.ok(result);
    }

}
