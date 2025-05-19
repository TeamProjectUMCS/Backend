package com.team.backend.model.Enum;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public enum Preference {
    @JsonProperty("Men")
    MEN("Men"),
    @JsonProperty("Women")
    WOMEN("Women"),
    @JsonProperty("Both")
    BOTH("Both"),
    @JsonProperty("Other")
    OTHER("Other");

    private final String displayName;

    Preference(String displayName) {
        this.displayName = displayName;
    }

}
