package arden.java.multithreadingtranslator.clients;

import java.util.Optional;

public interface RestTemplateClient {
    Optional<String> fetchRequest(String word, String language, String source);
}
