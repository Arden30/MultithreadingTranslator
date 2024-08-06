package arden.java.multithreadingtranslator.clients;

import arden.java.multithreadingtranslator.api.dto.response.TranslateResponse;
import arden.java.multithreadingtranslator.configuration.APIKeyConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
public class RestTemplateClientImpl implements RestTemplateClient {
    private final APIKeyConfig apiKeyConfig;

    @Override
    public Optional<String> fetchRequest(String word, String language, String source) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Api-Key " + apiKeyConfig.key());

        Map<String, String> formData = new HashMap<>();
        formData.put("targetLanguageCode", language);
        formData.put("texts", word);

        HttpEntity<Map<String, String>> requestEntity = new HttpEntity<>(formData, headers);
        ResponseEntity<TranslateResponse> response = restTemplate.postForEntity(source, requestEntity, TranslateResponse.class);

        return Optional.of(Objects.requireNonNull(response.getBody()).translations().getFirst().text());
    }
}
