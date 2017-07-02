package org.erhmutlu.jbeat.service.converter;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by erhmutlu on 02/07/17.
 */

@Component(value = "jbeatMessageConverter")
public class JBeatMessageConverter extends Jackson2JsonMessageConverter{

    @Override
    protected Message createMessage(Object objectToConvert, MessageProperties messageProperties) {

        RabbitDsl dsl = new RabbitDsl((Map<String, Object>) objectToConvert);

        return super.createMessage(dsl, messageProperties);
    }
}
