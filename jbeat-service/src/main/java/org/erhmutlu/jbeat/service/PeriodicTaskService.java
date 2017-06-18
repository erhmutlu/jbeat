package org.erhmutlu.jbeat.service;

import org.erhmutlu.jbeat.api.exceptions.JBeatException;
import org.erhmutlu.jbeat.persistency.models.PeriodicTask;

import java.util.Map;

/**
 * Created by erhmutlu on 08/06/17.
 */
public interface PeriodicTaskService {

    PeriodicTask createPeriodicTask(String taskName, String queue, String crontab, Map params, String description) throws JBeatException;

    PeriodicTask updateCrontabByTaskName(String taskName, String newCrontab) throws JBeatException;

    PeriodicTask getTaskByName(String taskName) throws JBeatException;

    PeriodicTask getTaskByNameOrQueue(String taskName, String queue) throws JBeatException;

    boolean checkExistByTaskNameOrQueue(String taskName, String queue);

    PeriodicTask setActiveByTaskName(String taskName, Boolean isActive) throws JBeatException;
}
