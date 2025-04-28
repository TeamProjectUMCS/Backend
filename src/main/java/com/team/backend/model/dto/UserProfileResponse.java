package com.team.backend.model.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record UserProfileResponse(
        String username,
        String description,
        String profilePictureUrl,
        List<String> mediaUrls
) {
}