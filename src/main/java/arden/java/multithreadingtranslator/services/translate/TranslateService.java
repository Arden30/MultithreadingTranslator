package arden.java.multithreadingtranslator.services.translate;

import arden.java.multithreadingtranslator.api.dto.request.TranslateRequest;

public interface TranslateService {
    String translate(TranslateRequest translateRequest);
}
