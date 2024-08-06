package arden.java.multithreadingtranslator.configuration;

import arden.java.multithreadingtranslator.clients.RestTemplateClient;
import arden.java.multithreadingtranslator.clients.RestTemplateClientImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
@RequiredArgsConstructor
public class ClientConfiguration {
    private final APIKeyConfig apiKeyConfig;

    @Bean
    public RestTemplateClient restTemplateClient() {
        return new RestTemplateClientImpl(apiKeyConfig);
    }
}
