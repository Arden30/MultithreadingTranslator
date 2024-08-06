package arden.java.multithreadingtranslator.model;

import lombok.Data;

@Data
public class UserResult {
    private String ipAddress;
    private String inputText;
    private String translatedText;
}
