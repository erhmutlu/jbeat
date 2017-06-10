package org.erhmutlu.jbeat.service;

import org.erhmutlu.jbeat.persistency.models.PeriodicTask;
import org.erhmutlu.jbeat.service.schedule.ScheduledJob;
import org.erhmutlu.jbeat.service.schedule.ScheduledJobRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Created by erhmutlu on 08/06/17.
 */

@Service
public class ScheduledJobRegistryServiceImpl implements ScheduledJobRegistryService{

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private ScheduledJobRegistry scheduledJobRegistry;
    private RabbitWriterService rabbitWriterService;
    private PeriodicTaskService periodicTaskService;

    public ScheduledJobRegistryServiceImpl(ScheduledJobRegistry scheduledJobRegistry, RabbitWriterService rabbitWriterService, PeriodicTaskService periodicTaskService) {
        this.scheduledJobRegistry = scheduledJobRegistry;
        this.rabbitWriterService = rabbitWriterService;
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
        logger.info("ScheduledJobRegistryService register(periodicTask: {})", periodicTask);
        ScheduledJob scheduledJob = findSchedulerByTaskName(periodicTask.getTaskName());
        if (scheduledJob == null){
            logger.info("Scheduled Job has not registered before, taskName: {}", periodicTask.getTaskName());
            scheduledJob = new ScheduledJob(periodicTask, rabbitWriterService, periodicTaskService);
            scheduledJobRegistry.put(scheduledJob);
        }else {
            logger.info("Scheduled Job has registered before, taskName: {}\n periodicTask instance will be updated.", periodicTask.getTaskName());

            scheduledJob.setPeriodicTask(periodicTask);
        }

        scheduledJob.schedule();
        return scheduledJob;
    }

    private ScheduledJob findSchedulerByTaskName(String taskName){
        logger.info("ScheduledJobRegistryService findSchedulerByTaskName(taskName: {})", taskName);

        return scheduledJobRegistry.get(taskName);
    }

}