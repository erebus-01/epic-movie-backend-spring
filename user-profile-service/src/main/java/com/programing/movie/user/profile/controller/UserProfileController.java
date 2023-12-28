package com.programing.movie.user.profile.controller;

import com.programing.movie.user.profile.dto.UpdatePasswordRequest;
import com.programing.movie.user.profile.dto.UserProfileRequest;
import com.programing.movie.user.profile.dto.UserProfileResponse;
import com.programing.movie.user.profile.impl.UserProfileServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/user-profile")
@RequiredArgsConstructor
public class UserProfileController {

    private final UserProfileServiceImpl service;

    @GetMapping("/profile/{profileId}")
    public ResponseEntity<Optional<UserProfileResponse>> getUserProfilesById(
            @PathVariable("profileId") Long profileId) {
        Optional<UserProfileResponse> userProfiles = service.getUserProfileById(profileId);
        return ResponseEntity.ok(userProfiles);
    }

    @GetMapping("/user-id/{userId}")
    public ResponseEntity<List<UserProfileResponse>> getUserProfilesByUserId(
            @PathVariable("userId") UUID userId) {
        List<UserProfileResponse> userProfiles = service.getUserProfileByUserId(userId);
        return ResponseEntity.ok(userProfiles);
    }

    @GetMapping("/{profileId}/{userId}")
    public ResponseEntity<UserProfileResponse> getUserProfileByUserId(
            @PathVariable("profileId") Long profileId,
            @PathVariable("userId") UUID userId) {
        UserProfileResponse userProfile = service.getUserProfileByIdAndUserId(profileId, userId);
        return ResponseEntity.ok(userProfile);
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserProfileResponse>> getAllUserProfiles() {
        List<UserProfileResponse> userProfiles = service.getAllUserProfiles();
        return ResponseEntity.ok(userProfiles);
    }

    @PostMapping("/create")
    public ResponseEntity<UserProfileResponse> createUserProfile(
            @RequestBody UserProfileRequest userProfileDTO) {
        UserProfileResponse createdUserProfile = service.createUserProfile(userProfileDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUserProfile);
    }

    @PutMapping("/update/{userProfileId}/{userId}")
    public ResponseEntity<UserProfileResponse> updateUserProfile(
            @PathVariable("userProfileId") Long userProfileId,
            @RequestBody UserProfileRequest userProfileDTO) {
        UserProfileResponse updatedUserProfile = service.updateUserProfile(userProfileId, userProfileDTO);
        if (updatedUserProfile != null) {
            return ResponseEntity.ok(updatedUserProfile);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}/update-password")
    public ResponseEntity<UserProfileResponse> updatePassword(
            @PathVariable("id") Long id,
            @RequestBody UpdatePasswordRequest newPassword) {
        UserProfileResponse updatedUserProfile = service.updateUserPassword(id, newPassword);
        return ResponseEntity.ok(updatedUserProfile);
    }

    @DeleteMapping("/delete/{userProfileId}")
    public ResponseEntity<String> deleteUserProfile(@PathVariable("userProfileId") Long userProfileId) {
        String resultMessage = service.deleteUserProfile(userProfileId);
        return ResponseEntity.ok(resultMessage);
    }

}
