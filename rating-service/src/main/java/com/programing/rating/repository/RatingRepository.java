package com.programing.rating.repository;

import com.programing.rating.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {
    List<Rating> findByMovie(UUID movieId);

    List<Rating> findByUserProfile(Long userProfileId);
    void deleteByUserProfile(Long userProfileId);

    void deleteByMovie(UUID movieId);


    @Query("SELECT r FROM Rating r WHERE r.userProfile = :userProfile AND r.movie = :movie")
    List<Rating> findByUserProfileAndMovie(@Param("userProfile") Long userProfile, @Param("movie") UUID movie);

}
