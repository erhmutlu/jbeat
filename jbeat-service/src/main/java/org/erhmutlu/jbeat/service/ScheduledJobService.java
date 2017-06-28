package org.erhmutlu.jbeat.service;

import org.erhmutlu.jbeat.api.ScheduledJob;
import org.erhmutlu.jbeat.api.exceptions.JBeatException;
import org.erhmutlu.jbeat.persistency.models.PeriodicTask;

/**
 * Created by erhmutlu on 10/06/17.
 */
public interface ScheduledJobService {

    ScheduledJob schedule(PeriodicTask periodicTask);

    ScheduledJob reschedule(String taskName, String newCrontab) throws JBeatException;

    void disable(String taskName);
}
