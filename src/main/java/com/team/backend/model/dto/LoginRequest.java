package com.team.backend.model.dto;

public record LoginRequest(
        String username,
        String login,
        String password
) {
}
