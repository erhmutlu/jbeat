package org.erhmutlu.jbeat.api;

import org.erhmutlu.jbeat.persistency.models.PeriodicTask;

/**
 * Created by erhmutlu on 28/06/17.
 */
public interface ScheduledJob extends Runnable {


    void schedule();

    void stop();

    void setPeriodicTask(PeriodicTask periodicTask);

    PeriodicTask getTask();
}
