package org.erhmutlu.jbeat.service;

import org.erhmutlu.jbeat.api.exceptions.JBeatException;
import org.erhmutlu.jbeat.service.schedule.ScheduledJob;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * Created by erhmutlu on 16/06/17.
 */
public interface JBeatFacade {
    ScheduledJob scheduleNewTask(String taskName, String queue, String crontab, Map params, String description) throws JBeatException;

    @Transactional
    void disableTask(String taskName) throws JBeatException;
}
