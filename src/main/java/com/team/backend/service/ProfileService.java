package com.team.backend.service;

import com.team.backend.model.Profile;
import com.team.backend.model.User;
import com.team.backend.model.UserMedia;
import com.team.backend.model.dto.ProfilePictureDto;
import com.team.backend.model.dto.UserProfileResponse;
import com.team.backend.model.mapper.ProfileMapper;
import com.team.backend.repository.ProfileRepository;
import com.team.backend.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@AllArgsConstructor
public class ProfileService implements IProfileService{

    private final ProfileRepository profileRepository;
    private final UserRepository userRepository;
    private final ProfileMapper profileMapper;

    @Override
    public UserProfileResponse getUserProfileByUsername(String username) {
        Profile profile = profileRepository.findProfileByUserUsername(username)
                .orElseThrow(() -> new RuntimeException("Profile not found"));

        return profileMapper.toProfileResponse(profile);
    }

    @Override
    public ProfilePictureDto getUserProfilePictureByUsername(String username) {
        String profilePictureUrl = profileRepository.findProfilePictureUrlByUserUsername(username)
                .orElseThrow(() -> new RuntimeException("Profile picture not found"));

        return new ProfilePictureDto(profilePictureUrl);
    }

    @Override
    public void changeProfilePicture(String username, String newProfilePictureUrl) {
        Profile profile = profileRepository.findProfileByUserUsername(username)
                .orElseThrow(() -> new RuntimeException("Profile not found"));

        profile.setProfilePictureUrl(newProfilePictureUrl);
        profileRepository.save(profile);
    }

    @Override
    public void updateProfileDescription(String username, String newDescription) {
        Profile profile = profileRepository.findProfileByUserUsername(username)
                .orElseThrow(() -> new RuntimeException("Profile not found"));

        profile.setDescription(newDescription);
        profileRepository.save(profile);
    }

    @Override
    public void addMediaFile(String username, String mediaFileUrl) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("User not found with username: " + username));

        Profile profile = user.getProfile();

        UserMedia userMedia = new UserMedia();
        userMedia.setMediaUrl(mediaFileUrl);
        userMedia.setUser(user);
        userMedia.setProfile(profile);

        if (profile.getMediaFiles() == null) {
            profile.setMediaFiles(new ArrayList<>());
        }
        profile.getMediaFiles().add(userMedia);

        userRepository.save(user);
    }

}
