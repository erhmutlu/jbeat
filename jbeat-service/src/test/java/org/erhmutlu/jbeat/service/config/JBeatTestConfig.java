package org.erhmutlu.jbeat.service.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Created by erhmutlu on 08/06/17.
 */

@TestConfiguration
@EntityScan(basePackages = "org.erhmutlu.jbeat.persistency.models")
@EnableJpaRepositories(basePackages = "org.erhmutlu.jbeat.persistency.dao")
@EnableAutoConfiguration
@ComponentScan(basePackages = {"org.erhmutlu.jbeat"})
public class JBeatTestConfig {
}
