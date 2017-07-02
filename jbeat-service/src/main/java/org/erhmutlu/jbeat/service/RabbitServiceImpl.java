package org.erhmutlu.jbeat.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by erhmutlu on 10/06/17.
 */

@Service
//@ConditionalOnClass(RabbitTemplate.class)
public class RabbitServiceImpl implements RabbitService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private RabbitTemplate rabbitTemplate;
    private AmqpAdmin amqpAdmin;

    public RabbitServiceImpl(@Qualifier(value = "jbeatRabbitTemplate") RabbitTemplate rabbitTemplate, @Qualifier(value = "jbeatAmqpAdmin") AmqpAdmin amqpAdmin) {
        this.rabbitTemplate = rabbitTemplate;
        this.amqpAdmin = amqpAdmin;
    }

    @Override
    public void write(String queueName, Map<String, Object> map){
        logger.info("RabbitService write(queueName: {}, map: {})", queueName, map);

        declareQueue(queueName); //safe to call declareQueues, RabbitAdmin declares if the queue is not exist!

        rabbitTemplate.convertAndSend(queueName, map);
    }

    @Override
    public void declareQueue(String queueName){
        logger.info("RabbitService declareQueue(queueName: {})", queueName);
        amqpAdmin.declareQueue(new Queue(queueName, true));
        logger.info("Queue is declared!");
    }

}
