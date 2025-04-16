package com.team.backend.model.Enum;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;


@Getter
public enum Sex {
    @JsonProperty("Male")
    MALE("Male"),
    @JsonProperty("Female")
    FEMALE("Female"),
    @JsonProperty("Other")
    OTHER("Other");

    private final String displayName;

    Sex(String displayName) {
        this.displayName = displayName;
    }
}
