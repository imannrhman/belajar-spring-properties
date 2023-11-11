package imannrhman.belajarspringproperties.spring.config.valuesproperties;

import lombok.Getter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;


@SpringBootTest(classes = ValuesTest.TestApplication.class)
public class ValuesTest {

    @Autowired
    private TestApplication.ApplicationProperties properties;

    @Autowired
    private TestApplication.SystemProperties systemProperties;


    @Test
    void testValue() {
        Assertions.assertEquals(2, properties.getVersion());
    }

    @Test
    void testSystemProperties() {
        Assertions.assertEquals("/Users/imannrhman/.jenv/versions/17.0.2", systemProperties.getJavaHome());
    }

    @SpringBootApplication
   public static class TestApplication {

        @Component
        @Getter
        public static  class SystemProperties {

            @Value("${JAVA_HOME}")
            private String javaHome;
        }

        @Component
        @Getter
        public static class ApplicationProperties {

            @Value("${application.name}")
            private String name;

            @Value("${application.version}")
            private int version;

            @Value("${application.production-mode}")
            private boolean productionMode;


        }

    }
}
