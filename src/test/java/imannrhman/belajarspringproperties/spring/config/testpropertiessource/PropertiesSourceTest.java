package imannrhman.belajarspringproperties.spring.config.testpropertiessource;

import lombok.Getter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.stereotype.Component;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.TestPropertySources;


@SpringBootTest(classes = PropertiesSourceTest.PropertiesSources.class)
@TestPropertySources({
        @TestPropertySource("classpath:/test.properties")
})
public class PropertiesSourceTest {

    @Autowired
    private  PropertiesSources.SampleProperties properties;

    @Test
    void testPropertiesSource() {
        Assertions.assertEquals(1, properties.getVersion());
    }

    @SpringBootApplication
    public static class PropertiesSources {

        @Component
        @Getter
        public static class SampleProperties {

            @Value("${sample.name}")
            private String name;

            @Value("${sample.version}")
            private int version;
        }
    }
}
