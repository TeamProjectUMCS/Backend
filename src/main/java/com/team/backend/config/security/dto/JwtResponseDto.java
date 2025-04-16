package com.team.backend.config.security.dto;

public record JwtResponseDto(
        String username,
        String token
) {
}