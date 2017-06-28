package org.erhmutlu.jbeat.service.config;

import org.erhmutlu.jbeat.api.JBeatProperties;
import org.erhmutlu.jbeat.persistency.dao.PeriodicTaskDao;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import static org.mockito.Mockito.*;

/**
 * Created by erhmutlu on 08/06/17.
 */

@TestConfiguration
@EntityScan(basePackages = "org.erhmutlu.jbeat.persistency.models")
@EnableJpaRepositories(basePackages = "org.erhmutlu.jbeat.persistency.dao")
@EnableAutoConfiguration
@ComponentScan(basePackages = {"org.erhmutlu.jbeat"})
@EnableConfigurationProperties(JBeatProperties.class)
public class JBeatTestConfig {}
