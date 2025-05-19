package com.team.backend.model.dto;

public record MatchDto(
        Long matchId,
        Long userId,
        String username
) {}
