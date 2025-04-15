package com.team.backend.model.dto;

public record RegisterResponseDto(
        String username,
        String login,
        String message
) {
}