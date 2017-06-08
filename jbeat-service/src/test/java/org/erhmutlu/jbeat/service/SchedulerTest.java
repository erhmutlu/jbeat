package org.erhmutlu.jbeat.service;

import org.junit.Test;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;

import java.util.concurrent.ScheduledFuture;

/**
 * Created by erhmutlu on 05/06/17.
 */
public class SchedulerTest {
    private ScheduledFuture scheduledFuture;
    private TaskScheduler taskScheduler ;

    @Test
    public void testMe() throws InterruptedException {
        Runnable runnable = () -> {
            System.out.println("Hey!");
        };

        schedule(runnable, "0 46 22 ? * *");
        Thread.sleep(120000l);
    }

    public void schedule(Runnable runnable, String crontab){
        if(taskScheduler== null){
            this.taskScheduler = new ConcurrentTaskScheduler();
        }
        if (this.scheduledFuture != null) {
            cancel();
        }
        this.scheduledFuture = this.taskScheduler.schedule(runnable, new CronTrigger(crontab));
    }

    public void cancel(){
        this.scheduledFuture.cancel(true);
    }
}
