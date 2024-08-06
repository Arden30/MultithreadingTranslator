package arden.java.multithreadingtranslator.services.db;

import arden.java.multithreadingtranslator.api.dto.request.TranslateRequest;
import arden.java.multithreadingtranslator.model.UserResult;
import arden.java.multithreadingtranslator.repository.TranslateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DBServiceImpl implements DBService {
    private final TranslateRepository translateRepository;

    @Override
    public void saveTranslation(String ip, TranslateRequest translateRequest, String translatedText) {
        UserResult userResult = new UserResult();
        userResult.setIpAddress(ip);
        userResult.setTranslatedText(translatedText);
        userResult.setInputText(translateRequest.text());

        translateRepository.saveResult(userResult);
    }
}
