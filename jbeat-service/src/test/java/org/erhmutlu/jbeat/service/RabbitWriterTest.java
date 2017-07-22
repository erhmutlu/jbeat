package org.erhmutlu.jbeat.service;

import org.erhmutlu.jbeat.persistency.models.PeriodicTask;
import org.erhmutlu.jbeat.service.config.BaseTest;
import org.junit.Test;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by erhmutlu on 02/07/17.
 */
public class RabbitWriterTest extends BaseTest {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    RabbitService rabbitService;

    @Autowired
    RabbitAdmin rabbitAdmin;

    @Test
    public void testMe(){
        logger.info("RabbitWriterTest testMe is beginning");

        PeriodicTask task = randomPeriodicTaskInstance();
        task.setQueue("test-queue1111");

        rabbitService.write(task);

    }
}
