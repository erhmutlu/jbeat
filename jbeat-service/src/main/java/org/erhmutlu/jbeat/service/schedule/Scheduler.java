package org.erhmutlu.jbeat.service.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;

import java.util.concurrent.ScheduledFuture;

/**
 * Created by erhmutlu on 28/06/17.
 */
public class Scheduler {

    private ScheduledFuture scheduledFuture;
    private TaskScheduler taskScheduler;

    public Scheduler() {
        initiliaze();
    }

    private void initiliaze(){
        if(taskScheduler == null){
            taskScheduler = new ConcurrentTaskScheduler();
        }
    }

    public void schedule(String crontab, Runnable runnable){
        stopIfScheduled();
        scheduledFuture = taskScheduler.schedule(runnable, new CronTrigger(crontab));
    }

    public void scheduleAtFixedRate(Long period, Runnable runnable){
        stopIfScheduled();
        scheduledFuture = taskScheduler.scheduleAtFixedRate(runnable, period);
    }

    private void stopIfScheduled(){
        if (this.scheduledFuture != null) {
            stop();
        }
    }

    public void stop(){
        scheduledFuture.cancel(true);
    }

}
