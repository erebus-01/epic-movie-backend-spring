package com.programing.movie.user.profile.repository;

import com.programing.movie.user.profile.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {

    UserProfile findByIdAndUserId(Long id, UUID userId);

    List<UserProfile> findByUserId(UUID userId);

    long countByUserIdIn(Set<UUID> userId);

}
