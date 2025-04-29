package com.team.backend.model.dto;

public record MessageResponseDto(
        Long messageId,
        String content,
        Long writtenBy,
        Long timestamp
) {
}
