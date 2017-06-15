package org.erhmutlu.jbeat.service;

import org.erhmutlu.jbeat.api.exceptions.JBeatException;
import org.erhmutlu.jbeat.persistency.models.PeriodicTask;

/**
 * Created by erhmutlu on 08/06/17.
 */
public interface PeriodicTaskService {
    PeriodicTask updateCrontabByTaskName(String taskName, String newCrontab) throws JBeatException;

    PeriodicTask getByTaskName(String taskName) throws JBeatException;
}
