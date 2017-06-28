package org.erhmutlu.jbeat.service.schedule;

import org.erhmutlu.jbeat.api.ScheduledJob;
import org.erhmutlu.jbeat.api.exceptions.JBeatException;
import org.erhmutlu.jbeat.persistency.models.PeriodicTask;
import org.erhmutlu.jbeat.service.PeriodicTaskService;
import org.erhmutlu.jbeat.service.RabbitWriterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;

import java.util.concurrent.ScheduledFuture;

/**
 * Created by erhmutlu on 05/06/17.
 */
public class RabbitJob extends AbstractJob implements ScheduledJob{

    private PeriodicTask task;
    private RabbitWriterService rabbitWriterService;
    private PeriodicTaskService periodicTaskService;

    public RabbitJob(PeriodicTask periodicTask, RabbitWriterService rabbitWriterService, PeriodicTaskService periodicTaskService) {
        super();
        this.task = periodicTask;
        this.rabbitWriterService = rabbitWriterService;
        this.periodicTaskService = periodicTaskService;
    }


    @Override
    public void run(){
        logger.info(task.getTaskName() + " is running!");
//        rabbitWriterService.write(task.getQueue(), task.getParams());
//        periodicTaskService.increaseTotalRunByTaskName(task.getTaskName());

    }

    @Override
    public void schedule(){
        scheduler.schedule(task.getCrontab(), this);
    }

    @Override
    public void stop(){
        scheduler.stop();
    }

    @Override
    public void setPeriodicTask(PeriodicTask periodicTask) {
        task = periodicTask;
    }

    @Override
    public PeriodicTask getTask() {
        return task;
    }

    @Override
    public String toString() {
        return "RabbitJob{" +
                "task=" + task +
                '}';
    }

}
