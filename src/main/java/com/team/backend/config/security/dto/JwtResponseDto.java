package com.team.backend.config.security.dto;

public record JwtResponseDto(
        String login,
        String token,
        Long id
) {
}