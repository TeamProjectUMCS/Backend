package com.team.backend.model.dto;

public record MessageRequestDto(
        String content,
        Long writtenBy,
        Long matchId
) {}
