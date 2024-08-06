package arden.java.multithreadingtranslator.api.dto.request;

import jakarta.validation.constraints.NotNull;

public record TranslateRequest(
        @NotNull
        String text,
        @NotNull
        String sourceLanguage,
        @NotNull
        String targetLanguage) {
}
