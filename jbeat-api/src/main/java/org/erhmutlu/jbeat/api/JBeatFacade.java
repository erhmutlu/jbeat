package org.erhmutlu.jbeat.api;

import org.erhmutlu.jbeat.api.exceptions.JBeatException;

import java.util.Map;

/**
 * Created by erhmutlu on 16/06/17.
 */
public interface JBeatFacade {
    void scheduleNewTask(String taskName, String queue, String crontab, Map params, Boolean isActive, String description) throws JBeatException;
}
