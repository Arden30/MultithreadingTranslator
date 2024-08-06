package arden.java.multithreadingtranslator.api.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public class TranslatorException extends RuntimeException {
    private final String description = "Ошибка доступа к ресурсу";
    private final HttpStatusCode httpStatusCode = HttpStatus.BAD_REQUEST;

    public TranslatorException(String message) {
        super(message);
    }
}
