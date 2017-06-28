package org.erhmutlu.jbeat.service.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by erhmutlu on 28/06/17.
 */
public abstract class AbstractJob {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());


    protected Scheduler scheduler;

    public AbstractJob() {
        initiliazeScheduler();
    }

    protected void initiliazeScheduler(){
        this.scheduler = new Scheduler();
    }
}
