package org.erhmutlu.jbeat.service.converter;

import org.erhmutlu.jbeat.api.RabbitDsl;
import org.erhmutlu.jbeat.persistency.models.PeriodicTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.stereotype.Component;


/**
 * Created by erhmutlu on 02/07/17.
 */

@Component(value = "jbeatMessageConverter")
public class JBeatMessageConverter extends Jackson2JsonMessageConverter{

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    protected Message createMessage(Object objectToConvert, MessageProperties messageProperties) {

        PeriodicTask task = (PeriodicTask) objectToConvert;

        RabbitDsl dsl = new RabbitDsl(
                task.getParams(),
                task.getTaskName());

        return super.createMessage(dsl, messageProperties);
    }
}
