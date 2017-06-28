package org.erhmutlu.jbeat.service.schedule;

/**
 * Created by erhmutlu on 28/06/17.
 */
public class AutoDiscoverJob extends AbstractJob  {

    private Long rate;

    public AutoDiscoverJob(Long rate) {
        this.rate = rate;
    }

    @Override
    public void run() {
        logger.info("AutoDiscoverJob is running");
    }

    @Override
    public void schedule() {
        scheduler.scheduleAtFixedRate(rate, this);
    }

}
