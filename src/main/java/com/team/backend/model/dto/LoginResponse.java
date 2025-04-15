package com.team.backend.model.dto;

public record LoginResponse(
        String username,
        String login,
        String password
) {
}
