package org.erhmutlu.jbeat.service;

import org.erhmutlu.jbeat.api.ScheduledJob;
import org.erhmutlu.jbeat.api.exceptions.JBeatException;
import org.erhmutlu.jbeat.persistency.models.PeriodicTask;
import org.erhmutlu.jbeat.service.schedule.RabbitJob;
import org.erhmutlu.jbeat.service.schedule.ScheduledJobRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by erhmutlu on 08/06/17.
 */

@Service
public class ScheduledJobServiceImpl implements ScheduledJobService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private ScheduledJobRegistry scheduledJobRegistry;
    private RabbitService rabbitService;
    private PeriodicTaskService periodicTaskService;

    public ScheduledJobServiceImpl(ScheduledJobRegistry scheduledJobRegistry, RabbitService rabbitService, PeriodicTaskService periodicTaskService) {
        this.scheduledJobRegistry = scheduledJobRegistry;
        this.rabbitService = rabbitService;
        this.periodicTaskService = periodicTaskService;
    }

    /**
     * Schedules the given PeriodicTask using PeriodicTask.crontab field.
     *
     * If the given task is registered in the Registry, PeriodicTask is updated and ScheduledJob continues to work.
     *
     * @param periodicTask
     * @return
     */
    @Override
    public ScheduledJob schedule(PeriodicTask periodicTask){
        logger.info("ScheduledJobService register(periodicTask: {})", periodicTask);
        ScheduledJob job = findSchedulerByTaskName(periodicTask.getTaskName());
        if (job == null){
            logger.info("Scheduled Job has not registered before, taskName: {}", periodicTask.getTaskName());
            job = new RabbitJob(periodicTask, rabbitService, periodicTaskService);
            scheduledJobRegistry.put(job);
        }else {
            logger.info("Scheduled Job has registered before, taskName: {}\n periodicTask instance will be updated.", periodicTask.getTaskName());

            job.setPeriodicTask(periodicTask);
        }

        job.schedule();
        return job;
    }

    @Override
    @Transactional(rollbackFor = JBeatException.class)
    public ScheduledJob reschedule(String taskName, String newCrontab) throws JBeatException{
        logger.info("ScheduledJobService reschedule(taskName: {}, newCrontab: {})", taskName, newCrontab);

        //TODO extract this to JBeatFacade
        PeriodicTask periodicTask = periodicTaskService.updateCrontabByTaskName(taskName, newCrontab);
        return schedule(periodicTask);
    }

    @Override
    public void disable(String taskName){
        logger.info("ScheduledJobService disable(taskName: {}", taskName);

        ScheduledJob job = scheduledJobRegistry.get(taskName);
        if(job == null){
            logger.warn("Task was not found in the registry.\nWarning will be ignored.");
            return;
        }

        job.stop();
        logger.info("Task is stopped");
        scheduledJobRegistry.remove(taskName);
        logger.info("Task is removed from registry");

    }

    @Override
    public List<ScheduledJob> getAllRegisteredJobs(){
        logger.info("ScheduledJobService getAllRegisteredJobs");

        Map<String, ScheduledJob> all = scheduledJobRegistry.all();
        return all.values().stream().collect(Collectors.toList());
    }

    private ScheduledJob findSchedulerByTaskName(String taskName){
        logger.debug("ScheduledJobService findSchedulerByTaskName(taskName: {})", taskName);

        return scheduledJobRegistry.get(taskName);
    }

}
