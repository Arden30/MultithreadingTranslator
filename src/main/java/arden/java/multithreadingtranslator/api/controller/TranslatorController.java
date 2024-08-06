package arden.java.multithreadingtranslator.api.controller;

import arden.java.multithreadingtranslator.api.dto.request.TranslateRequest;
import arden.java.multithreadingtranslator.api.dto.response.Translation;
import arden.java.multithreadingtranslator.services.db.DBService;
import arden.java.multithreadingtranslator.services.translate.TranslateService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/multitranslator/api/v1")
@RequiredArgsConstructor
public class TranslatorController {
    private final DBService dbService;
    private final TranslateService translateService;

    @PostMapping("/translate")
    public ResponseEntity<Translation> translate(
            @RequestBody @Valid TranslateRequest translateRequest,
            HttpServletRequest userRequest
    ) {
        String ip = userRequest.getRemoteAddr();
        String translatedText = translateService.translate(translateRequest);
        dbService.saveTranslation(ip, translateRequest, translatedText);

        return ResponseEntity.ok(new Translation(translatedText));
    }
}
