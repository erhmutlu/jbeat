package org.erhmutlu.jbeat.service.schedule;

/**
 * Created by erhmutlu on 28/06/17.
 */
public abstract class AbstractJob {

    protected Scheduler scheduler;

    public AbstractJob() {
        initiliazeScheduler();
    }

    protected void initiliazeScheduler(){
        this.scheduler = new Scheduler();
    }
}
