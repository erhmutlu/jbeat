package org.erhmutlu.jbeat.service.schedule;

import org.erhmutlu.jbeat.api.ScheduledJob;
import org.erhmutlu.jbeat.persistency.models.PeriodicTask;

/**
 * Created by erhmutlu on 28/06/17.
 */
public class AutoDiscoverJob extends AbstractJob implements ScheduledJob {

    private Long rate;

    public AutoDiscoverJob(Long rate) {
        this.rate = rate;
    }

    @Override
    public void run() {
        logger.info("AutoDiscoverJob is running");
    }

    @Override
    public void stop() {
        scheduler.stop();
    }

    @Override
    public void schedule() {
        scheduler.scheduleAtFixedRate(rate, this);
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
