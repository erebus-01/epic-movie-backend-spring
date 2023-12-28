package com.programing.movie.user.profile.impl;

import com.programing.movie.user.profile.dto.UpdatePasswordRequest;
import com.programing.movie.user.profile.dto.UserProfileRequest;
import com.programing.movie.user.profile.dto.UserProfileResponse;
import com.programing.movie.user.profile.model.UserProfile;
import com.programing.movie.user.profile.repository.UserProfileRepository;
import com.programing.movie.user.profile.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserProfileServiceImpl implements UserProfileService {

    public static final String URL_IMAGE_DEFAULT = "https://occ-0-58-64.1.nflxso.net/dnm/api/v6/vN7bi_My87NPKvsBoib006Llxzg/AAAABZ757RLEgtR5scrShGcYIXMVJ4u-Nq9MMR5DBTdY0h_2xbLluQhfqAnN_X-nNW30Db0v_jKUwBzUNLR_0Lq2XkXAPJMnvTY0UQ.png?r=eea";
    private final UserProfileRepository repository;

    @Override
    public UserProfileResponse createUserProfile(UserProfileRequest userProfileRequest) {
        if (countUserProfilesByUserId(userProfileRequest.getUserId()) >= 5) {
            throw new RuntimeException("User has reached the maximum limit of profiles.");
        }

        UserProfile userProfile = UserProfile.builder()
                .userId(userProfileRequest.getUserId())
                .profileName(userProfileRequest.getProfileName())
                .email(userProfileRequest.getEmail())
                .locked(false)
                .imageAvt(Objects.requireNonNullElse(userProfileRequest.getImageAvt(), URL_IMAGE_DEFAULT))
                .build();

        UserProfile savedUserProfile = repository.save(userProfile);
        return mapToUserProfileResponse(savedUserProfile);
    }

    @Override
    public Optional<UserProfileResponse> getUserProfileById(Long id) {
        Optional<UserProfile> userProfileOptional = repository.findById(id);
        return userProfileOptional.map(this::mapToUserProfileResponse);
    }

    @Override
    public List<UserProfileResponse> getAllUserProfiles() {
        List<UserProfile> userProfiles = repository.findAll();
        return userProfiles.stream()
                .map(this::mapToUserProfileResponse)
                .collect(Collectors.toList());
    }

    @Override
    public UserProfileResponse updateUserProfile(Long id, UserProfileRequest userProfileRequest) {
        Optional<UserProfile> existingUserProfileOptional = repository.findById(id);

        if (existingUserProfileOptional.isPresent()) {
            UserProfile existingUserProfile = existingUserProfileOptional.get();
            existingUserProfile.setUserId(userProfileRequest.getUserId());
            existingUserProfile.setProfileName(userProfileRequest.getProfileName());
            existingUserProfile.setEmail(userProfileRequest.getEmail());
            existingUserProfile.setLocked(false);
            existingUserProfile.setWatchedMovieIds(userProfileRequest.getWatchedMovieIds());
            existingUserProfile.setSearchMovieIds(userProfileRequest.getSearchMovieIds());

            UserProfile updatedUserProfile = repository.save(existingUserProfile);
            return mapToUserProfileResponse(updatedUserProfile);
        } else {
            return null;
        }
    }

    @Override
    public String deleteUserProfile(Long id) {
        return null;
    }

    @Override
    public UserProfileResponse getUserProfileByIdAndUserId(Long id, UUID userId) {
        UserProfile userProfiles = repository.findByIdAndUserId(id, userId);

        return mapToUserProfileResponse(userProfiles);
    }

    @Override
    public List<UserProfileResponse> getUserProfileByUserId(UUID userId) {
        List<UserProfile> userProfiles = repository.findByUserId(userId);

        return userProfiles.stream()
                .map(this::mapToUserProfileResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public UserProfileResponse updateUserPassword(Long id, UpdatePasswordRequest newPassword) {
        Optional<UserProfile> userProfileOptional = repository.findById(id);

        if (userProfileOptional.isPresent()) {
            UserProfile userProfile = userProfileOptional.get();
            userProfile.setPassword(newPassword.getPassword());
            userProfile.setLocked(true);
            repository.save(userProfile);

            return mapToUserProfileResponse(userProfile);
        } else {
            throw new RuntimeException("User profile not found with id: " + id);
        }
    }

    private UserProfileResponse mapToUserProfileResponse(UserProfile userProfile) {
        UserProfileResponse userProfileResponse = new UserProfileResponse();
        BeanUtils.copyProperties(userProfile, userProfileResponse);
        return userProfileResponse;
    }

    private long countUserProfilesByUserId(Set<UUID> userId) {
        return repository.countByUserIdIn(userId);
    }
}
