package imannrhman.belajarspringproperties.spring.config.profieenvironment;

import lombok.Setter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ActiveProfiles;


@SpringBootTest(classes = ProfileEnvironmentTest.TestApplication.class)
@ActiveProfiles("staging")
public class ProfileEnvironmentTest {

    @Autowired
    TestApplication.SampleProfile sampleProfile;

    @Test
    void testProfileEnvironment() {
        Assertions.assertEquals("staging", sampleProfile.getProfile()[0]);
    }

    @SpringBootApplication
    public static class TestApplication {

        @Component
        @Setter
        public  static  class SampleProfile implements EnvironmentAware {

            private Environment environment;

            public String[] getProfile() {
                return environment.getActiveProfiles();
            }
        }
    }
}
