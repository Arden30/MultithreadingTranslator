package arden.java.multithreadingtranslator.repository;

import arden.java.multithreadingtranslator.model.UserResult;

public interface TranslateRepository {
    void saveResult(UserResult userResult);
}
