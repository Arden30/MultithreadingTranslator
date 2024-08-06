package arden.java.multithreadingtranslator.configuration;

import jakarta.validation.constraints.NotNull;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Validated
@ConfigurationProperties(prefix = "api", ignoreUnknownFields = false)
public record APIKeyConfig(
        @NotNull
        String key
) {
}