package imannrhman.belajarspringproperties.spring.config.messagesource;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;

import java.util.Locale;

public class MessageSourceTest {

    private MessageSource messageSource;

    @BeforeEach
    void setUp() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(TestApplication.class);
        messageSource = applicationContext.getBean(MessageSource.class);
    }

    @Test
    void testDefaultLocale() {
        String message = messageSource.getMessage("hello", new Object[]{"Iman"}, Locale.ENGLISH);
        Assertions.assertEquals("Hello Iman", message);
    }

    @Test
    void testIndonesianLocale() {
        String message = messageSource.getMessage("hello", new Object[]{"Iman"}, new Locale("in_ID"));
        Assertions.assertEquals("Hallo Iman", message);
    }

    @SpringBootApplication
    public static class TestApplication {

        @Bean
        public MessageSource messageSource() {
            ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
            messageSource.setBasenames("my");
            return messageSource;

        }
    }
}
