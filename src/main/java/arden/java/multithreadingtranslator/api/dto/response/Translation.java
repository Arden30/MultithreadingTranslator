package arden.java.multithreadingtranslator.api.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Translation(
        @JsonProperty("text")
        String text
) {
}
