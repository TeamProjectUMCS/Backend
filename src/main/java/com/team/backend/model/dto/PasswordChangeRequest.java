package com.team.backend.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record PasswordChangeRequest(
        @NotBlank
        String oldPassword,

        @NotBlank
        @Size(min = 4, message = "Password must be at least 4 characters long")
        String newPassword
) {
}
