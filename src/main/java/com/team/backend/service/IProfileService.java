package com.team.backend.service;

import com.team.backend.model.dto.ProfilePictureDto;
import com.team.backend.model.dto.UserProfileResponse;

public interface IProfileService {
    public UserProfileResponse getUserProfileByUsername(String username);
    public ProfilePictureDto getUserProfilePictureByUsername(String username);
    public void changeProfilePicture(String username, String newProfilePictureUrl);
    public void updateProfileDescription(String username, String newDescription);
    public void addMediaFile(String username, String mediaFileUrl);
}
