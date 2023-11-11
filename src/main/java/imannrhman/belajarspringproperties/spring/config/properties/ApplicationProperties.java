package imannrhman.belajarspringproperties.spring.config.properties;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@ConfigurationProperties("application")
public class ApplicationProperties {

    private Duration defaultTimeout;

    private String name;

    private Integer version;

    private boolean productionMode;

    private DatabaseProperties databaseProperties;

    private List<Role> defaultRole;

    private Map<String, Role> roles;

    private Date expiredDate;
}


