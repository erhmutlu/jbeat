package org.erhmutlu.jbeat.service;

import java.util.Map;

/**
 * Created by erhmutlu on 10/06/17.
 */
public interface RabbitService {

    void write(String queueName, Map<String, Object> map);

    void declareQueue(String queueName);
}
