package com.team.backend.model.dto;

public record LoginResponseDto(
        String username,
        String login,
        String password
) {
}
