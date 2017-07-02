package org.erhmutlu.jbeat.service.config;

import com.fasterxml.jackson.databind.JsonSerializer;
import org.erhmutlu.jbeat.api.JBeatProperties;
import org.erhmutlu.jbeat.service.converter.JBeatDateSerializer;
import org.erhmutlu.jbeat.service.converter.JBeatMessageConverter;
import org.erhmutlu.jbeat.service.schedule.ScheduledJobRegistry;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Date;

/**
 * Created by erhmutlu on 05/06/17.
 */
@Configuration
//@ConditionalOnClass(name = "javax.persistence.EntityManagerFactory")
public class JBeatServiceConfig {


    @Bean(name = "jbeatRabbitTemplate")
    public RabbitTemplate jbeatRabbitTemplate(final ConnectionFactory connectionFactory, @Qualifier("jbeatMessageConverter") MessageConverter messageConverter) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter);
        return rabbitTemplate;
    }

    @Bean(name = "jbeatAmqpAdmin")
    public AmqpAdmin jbeatRabbitAdmin(final ConnectionFactory connectionFactory){
        return new RabbitAdmin(connectionFactory);
    }


}
