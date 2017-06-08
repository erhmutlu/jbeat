package org.erhmutlu.jbeat.service.config;

import org.erhmutlu.jbeat.api.JBeatProperties;
import org.erhmutlu.jbeat.api.Registry;
import org.erhmutlu.jbeat.persistency.dao.PeriodicTaskDao;
import org.erhmutlu.jbeat.service.schedule.ScheduledJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Created by erhmutlu on 05/06/17.
 */

@Configuration
@EnableConfigurationProperties(JBeatProperties.class)
public class JBeatServiceConfig {

    @Bean
    public Registry<ScheduledJob> scheduledJobRegistry(){
        return new Registry<>();
    }

}
