package org.erhmutlu.jbeat.service;

import org.erhmutlu.jbeat.api.ScheduledJob;
import org.erhmutlu.jbeat.api.exceptions.JBeatException;
import org.erhmutlu.jbeat.persistency.models.PeriodicTask;
import org.erhmutlu.jbeat.service.schedule.RabbitJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * Created by erhmutlu on 16/06/17.
 */

@Service
public class JBeatFacadeImpl implements JBeatFacade {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    private PeriodicTaskService periodicTaskService;
    private ScheduledJobService scheduledJobService;

    public JBeatFacadeImpl(PeriodicTaskService periodicTaskService, ScheduledJobService scheduledJobService) {
        this.periodicTaskService = periodicTaskService;
        this.scheduledJobService = scheduledJobService;
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public ScheduledJob scheduleNewTask(String taskName, String queue, String crontab, Map params, String description) throws JBeatException {
        logger.info("JBeatFacade createPeriodicTask(taskName: {}, crontab: {})",
                taskName, crontab);

        PeriodicTask periodicTask = periodicTaskService.createPeriodicTask(taskName, queue, crontab, params, description);
        logger.debug("Created PeriodicTask: {}", periodicTask);

        return scheduledJobService.schedule(periodicTask);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public void disableTask(String taskName) throws JBeatException{
        logger.info("JBeatFacade disableTask(taskName: {})", taskName);

        periodicTaskService.setActiveByTaskName(taskName, false);
        scheduledJobService.disable(taskName);

        logger.info("PeriodicTask: {} is disabled", taskName);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public void enableTask(String taskName) throws JBeatException{
        logger.info("JBeatFacade enableTask(taskName: {})", taskName);

        PeriodicTask periodicTask = periodicTaskService.setActiveByTaskName(taskName, true);
        scheduledJobService.schedule(periodicTask);

        logger.info("PeriodicTask: {} is enabled", taskName);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public void removeTask(String taskName) throws JBeatException{
        logger.info("JBeatFacade removeTask(taskName: {})", taskName);

        periodicTaskService.removeByTaskName(taskName);
        scheduledJobService.disable(taskName);

        logger.info("PeriodicTask: {} is removed", taskName);
    }


}
