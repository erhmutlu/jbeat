package org.erhmutlu.jbeat.autoconfigure;

import org.erhmutlu.jbeat.api.JBeatProperties;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Created by erhmutlu on 22/06/17.
 */

@Configuration
@ComponentScan(basePackages = {"org.erhmutlu.jbeat"})
@EntityScan(basePackages = "org.erhmutlu.jbeat.persistency.models")
@EnableJpaRepositories(basePackages = "org.erhmutlu.jbeat.persistency.dao")
@EnableConfigurationProperties(JBeatProperties.class)
public class JBeatAutoConfigure {
}
