package org.erhmutlu.jbeat.service.schedule;

import org.erhmutlu.jbeat.persistency.models.PeriodicTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;

import java.util.concurrent.ScheduledFuture;

/**
 * Created by erhmutlu on 05/06/17.
 */
public class ScheduledJob implements Runnable{
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private ScheduledFuture scheduledFuture;
    private TaskScheduler taskScheduler;

    private PeriodicTask task;
//    private RabbitWriterService rabbitWriterService;
//    private PeriodicTaskService periodicTaskService;

//    public ScheduledJob(PeriodicTask periodicTask, RabbitWriterService rabbitWriterService, PeriodicTaskService periodicTaskService) {
//        this.task = periodicTask;
//        this.rabbitWriterService = rabbitWriterService;
//        this.periodicTaskService = periodicTaskService;
//    }

    public void schedule(){
        if(taskScheduler== null){
            this.taskScheduler = new ConcurrentTaskScheduler();
        }
        if (this.scheduledFuture != null) {
            cancel();
        }
        this.scheduledFuture = this.taskScheduler.schedule(this, new CronTrigger(task.getCrontab()));
    }
    @Override
    public void run(){
        logger.info(task.getTaskName() + " is running!");
//        rabbitWriterService.write(task.getQueue(), task.getParams());
//        periodicTaskService.increaseTotalRunByTaskName(task.getTaskName());

    }

    public void cancel(){
        this.scheduledFuture.cancel(true);
    }

    public void setPeriodicTask(PeriodicTask periodicTask) {
        this.task = periodicTask;
    }
}
