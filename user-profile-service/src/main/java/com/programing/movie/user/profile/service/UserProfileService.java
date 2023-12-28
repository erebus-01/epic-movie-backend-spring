package com.programing.movie.user.profile.service;

import com.programing.movie.user.profile.dto.UpdatePasswordRequest;
import com.programing.movie.user.profile.dto.UserProfileRequest;
import com.programing.movie.user.profile.dto.UserProfileResponse;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface UserProfileService {
    UserProfileResponse createUserProfile(UserProfileRequest userProfileRequest);

    Optional<UserProfileResponse> getUserProfileById(Long id);

    List<UserProfileResponse> getAllUserProfiles();

    UserProfileResponse updateUserProfile(Long id, UserProfileRequest userProfileRequest);

    String deleteUserProfile(Long id);

    UserProfileResponse getUserProfileByIdAndUserId(Long id, UUID userId);

    List<UserProfileResponse> getUserProfileByUserId(UUID userId);

    UserProfileResponse updateUserPassword(Long id, UpdatePasswordRequest newPassword);

}
