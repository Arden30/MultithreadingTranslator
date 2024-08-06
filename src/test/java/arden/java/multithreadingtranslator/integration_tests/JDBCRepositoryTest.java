package arden.java.multithreadingtranslator.integration_tests;

import arden.java.multithreadingtranslator.model.UserResult;
import arden.java.multithreadingtranslator.repository.jdbc.JDBCTranslateRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@Rollback
public class JDBCRepositoryTest extends IntegrationTest {
    @Autowired
    private JDBCTranslateRepository jdbcTranslateRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final UserResult userResult = new UserResult();

    @BeforeEach
    void setUp() {
        userResult.setIpAddress("127.0.0.1");
        userResult.setInputText("hello");
        userResult.setTranslatedText("здравствуйте");
    }

    @BeforeEach
    @AfterEach
    public void restartIdentity() {
        jdbcTemplate.update("TRUNCATE translation RESTART IDENTITY");
    }

    @Test
    @DisplayName("Saving result in DB")
    public void test() {
        jdbcTranslateRepository.saveResult(userResult);

        assertThat(jdbcTemplate.queryForObject("SELECT ip FROM translation", String.class)).isEqualTo(userResult.getIpAddress());
        assertThat(jdbcTemplate.queryForObject("SELECT source_text FROM translation", String.class)).isEqualTo(userResult.getInputText());
        assertThat(jdbcTemplate.queryForObject("SELECT target_text FROM translation", String.class)).isEqualTo(userResult.getTranslatedText());
    }
}
