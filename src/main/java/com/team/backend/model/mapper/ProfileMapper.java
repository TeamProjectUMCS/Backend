package com.team.backend.model.mapper;

import com.team.backend.model.Profile;
import com.team.backend.model.UserMedia;
import com.team.backend.model.dto.UserProfileResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfileMapper {
    public UserProfileResponse toProfileResponse(Profile profile) {
        List<String> mediaUrls = profile.getMediaFiles().stream()
                .map(UserMedia::getMediaUrl)
                .toList();

        return UserProfileResponse.builder()
                .username(profile.getUser().getUsername())
                .description(profile.getDescription())
                .profilePictureUrl(profile.getProfilePictureUrl())
                .mediaUrls(mediaUrls)
                .build();
    }
}
