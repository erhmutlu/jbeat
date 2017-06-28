package org.erhmutlu.jbeat.service.schedule;

import org.erhmutlu.jbeat.api.ScheduledJob;
import org.erhmutlu.jbeat.persistency.models.PeriodicTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by erhmutlu on 28/06/17.
 */
public abstract class AbstractJob implements ScheduledJob {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());


    protected Scheduler scheduler;

    public AbstractJob() {
        initiliazeScheduler();
    }

    protected void initiliazeScheduler(){
        this.scheduler = new Scheduler();
    }

    public void stop() {
        scheduler.stop();
    }

    @Override
    public PeriodicTask getTask() {
        throw new RuntimeException("not implemented!");
    }

    @Override
    public void setPeriodicTask(PeriodicTask periodicTask) {
        throw new RuntimeException("not implemented!");
    }
}
