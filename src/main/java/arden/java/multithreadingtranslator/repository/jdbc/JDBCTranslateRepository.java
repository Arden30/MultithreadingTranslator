package arden.java.multithreadingtranslator.repository.jdbc;

import arden.java.multithreadingtranslator.model.UserResult;
import arden.java.multithreadingtranslator.repository.TranslateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class JDBCTranslateRepository implements TranslateRepository {
    private final JdbcTemplate jdbcTemplate;
    private final static String SAVE = """
            INSERT INTO translation(ip, source_text, target_text) VALUES (?, ?, ?)
            """;

    @Override
    public void saveResult(UserResult userResult) {
        jdbcTemplate.update(SAVE, userResult.getIpAddress(), userResult.getInputText(), userResult.getTranslatedText());
    }
}