package com.team.backend.controller;

import com.team.backend.model.dto.ProfilePictureDto;
import com.team.backend.model.dto.UserProfileResponse;
import org.springframework.http.ResponseEntity;

public interface IProfileController {
    public ResponseEntity<UserProfileResponse> getUserProfileByUsername(String username);
    public ResponseEntity<ProfilePictureDto> getUserProfilePictureByUsername(String username);
    public ResponseEntity<Void> updateProfileDescription(String username, String newDescription);
}
