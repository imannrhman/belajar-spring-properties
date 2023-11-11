package imannrhman.belajarspringproperties.spring.config.configurationproperties;

import imannrhman.belajarspringproperties.spring.config.converter.StringToDateConverter;
import imannrhman.belajarspringproperties.spring.config.properties.ApplicationProperties;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.convert.ApplicationConversionService;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.core.convert.ConversionService;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;


@SpringBootTest(classes = ConfigurationPropertiesTest.TestApplication.class)
public class ConfigurationPropertiesTest {

    @Autowired
    private ApplicationProperties applicationProperties;


    @Autowired
    private ConversionService conversionService;

    @Test
    void testApplicationProperties() {
        System.out.println( applicationProperties.getDatabaseProperties().getWhiteListTable());
    }

    @Test
    void testDuration() {
        Assertions.assertEquals(Duration.ofSeconds(10), applicationProperties.getDefaultTimeout());
    }


    @Test
    void testEmbeddedCollection() {
        System.out.println(applicationProperties.getRoles().get("admin").getName());
        Assertions.assertEquals("default", applicationProperties.getDefaultRole().get(0).getId());

    }

    @Test
    void testCustomConverter() {
        Date expireData = applicationProperties.getExpiredDate();
        var dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Assertions.assertEquals("2023-10-10", dateFormat.format(expireData));
    }

    @Test
    void testConversionService() {
        Assertions.assertTrue(conversionService.canConvert(String.class, Duration.class));
        Assertions.assertTrue(conversionService.canConvert(String.class, Date.class));

        Duration result = conversionService.convert("10s", Duration.class);
    }

    @SpringBootApplication
    @EnableConfigurationProperties({
            ApplicationProperties.class,
    })
    @Import(StringToDateConverter.class)
    public static class TestApplication {

        @Bean
        public ConversionService conversionService(StringToDateConverter stringToDateConverter) {
            ApplicationConversionService conversionService = new ApplicationConversionService();
            conversionService.addConverter(stringToDateConverter);
            return conversionService;
        }

    }
}
