package com.team.backend.model.dto;

import com.team.backend.model.Enum.Preference;
import com.team.backend.model.Enum.Sex;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record RegisterRequest(

        @NotNull(message = "{username.not.null}")
        @NotEmpty(message = "{username.not.empty}")
        @NotBlank(message = "{username.not.blank}")
        String username,

        @NotNull(message = "{login.not.null}")
        @NotEmpty(message = "{login.not.empty}")
        @NotBlank(message = "{login.not.blank}")
        String login,

        @NotNull(message = "{password.not.null}")
        @NotEmpty(message = "{password.not.empty}")
        @NotBlank(message = "{password.not.blank}")
        String password,

        Sex sex,
        Preference preference
) {
}
