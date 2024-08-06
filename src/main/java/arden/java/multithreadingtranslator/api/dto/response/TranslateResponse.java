package arden.java.multithreadingtranslator.api.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record TranslateResponse(
        @JsonProperty("translations")
        List<Translation> translations) {
}
