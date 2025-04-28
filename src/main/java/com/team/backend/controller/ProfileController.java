package com.team.backend.controller;

import com.team.backend.model.dto.ProfilePictureDto;
import com.team.backend.model.dto.UserProfileResponse;
import com.team.backend.service.IProfileService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profile")
@AllArgsConstructor
public class ProfileController implements IProfileController {

    private final IProfileService profileService;

    @Override
    @GetMapping("/{username}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<UserProfileResponse> getUserProfileByUsername(@PathVariable String username) {
        UserProfileResponse response = profileService.getUserProfileByUsername(username);
        return ResponseEntity.ok(response);
    }

    @Override
    @GetMapping("/{username}/picture")
    public ResponseEntity<ProfilePictureDto> getUserProfilePictureByUsername(@PathVariable String username) {
        ProfilePictureDto profilePicture = profileService.getUserProfilePictureByUsername(username);
        return ResponseEntity.ok(profilePicture);
    }

    @Override
    @PutMapping("/{username}/description")
    public ResponseEntity<Void> updateProfileDescription(
            @PathVariable String username,
            @RequestParam String newDescription) {

        profileService.updateProfileDescription(username, newDescription);
        return ResponseEntity.noContent().build();
    }

}

