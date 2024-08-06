package arden.java.multithreadingtranslator;

import arden.java.multithreadingtranslator.configuration.APIKeyConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({APIKeyConfig.class})
public class MultithreadingTranslatorApplication {

    public static void main(String[] args) {
        SpringApplication.run(MultithreadingTranslatorApplication.class, args);
    }

}
