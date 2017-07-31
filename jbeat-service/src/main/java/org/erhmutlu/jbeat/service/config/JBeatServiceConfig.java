package org.erhmutlu.jbeat.service.config;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
