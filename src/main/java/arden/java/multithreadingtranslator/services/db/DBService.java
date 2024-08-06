package arden.java.multithreadingtranslator.services.db;

import arden.java.multithreadingtranslator.api.dto.request.TranslateRequest;

public interface DBService {
    void saveTranslation(String ip, TranslateRequest translateRequest, String translatedText);
}
