package org.erhmutlu.jbeat.service;

import org.erhmutlu.jbeat.persistency.models.PeriodicTask;

/**
 * Created by erhmutlu on 10/06/17.
 */
public interface RabbitService {

    void write(PeriodicTask task);

    void declareQueue(String queueName);
}
