package arden.java.multithreadingtranslator.services.translate.yandex;

import arden.java.multithreadingtranslator.api.dto.request.TranslateRequest;
import arden.java.multithreadingtranslator.api.exceptions.TranslatorException;
import arden.java.multithreadingtranslator.clients.RestTemplateClient;
import arden.java.multithreadingtranslator.configuration.APIKeyConfig;
import arden.java.multithreadingtranslator.services.translate.TranslateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Service
@RequiredArgsConstructor
public class YandexTranslateService implements TranslateService {
    private final RestTemplateClient restTemplateClient;
    private final APIKeyConfig APIKeyConfig;
    private final static int NUM_OF_THREADS = 10;

    @Override
    public String translate(TranslateRequest translateRequest) {
        String yandexTranslateApiUrl = "https://translate.api.cloud.yandex.net/translate/v2/translate";
        ExecutorService executorService = Executors.newFixedThreadPool(NUM_OF_THREADS);
        String[] words = translateRequest.text().split("\\s+");
        Future<Optional<String>>[] futures = new Future[words.length];

        for (int i = 0; i < words.length; i++) {
            final int index = i;
            futures[i] = executorService.submit(() -> restTemplateClient.fetchRequest(words[index], translateRequest.targetLanguage(), yandexTranslateApiUrl));
        }

        StringBuilder translatedText = new StringBuilder();
        for (Future<Optional<String>> future : futures) {
            try {
                if (future.get().isPresent()) {
                    translatedText.append(future.get().get()).append(" ");
                }
            } catch (Exception e) {
                throw new TranslatorException("Ошибка доступа к ресурсу, проверьте правильность выбранного языка для перевода");
            }
        }

        return translatedText.toString();
    }
}
