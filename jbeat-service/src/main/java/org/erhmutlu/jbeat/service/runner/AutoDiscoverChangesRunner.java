package org.erhmutlu.jbeat.service.runner;

import org.erhmutlu.jbeat.api.JBeatProperties;
import org.erhmutlu.jbeat.api.ScheduledJob;
import org.erhmutlu.jbeat.service.PeriodicTaskService;
import org.erhmutlu.jbeat.service.ScheduledJobService;
import org.erhmutlu.jbeat.service.schedule.AutoDiscoverChangesJob;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.stereotype.Component;

/**
 * Created by erhmutlu on 21/06/17.
 */
@Component
public class AutoDiscoverChangesRunner extends JBeatRunner{

    @PersistenceConstructor
    public AutoDiscoverChangesRunner(JBeatProperties jBeatProperties, PeriodicTaskService periodicTaskService, ScheduledJobService scheduledJobService) {
        super(jBeatProperties, periodicTaskService, scheduledJobService);
    }

    @Override
    public void run(String... strings) throws Exception {
        logger.info("AutoDiscoverChangesRunner run is starting...");

        if(jBeatProperties.getRunner().isAutoDiscoverChangesEnabled()){
            logger.info("AutoDiscoverChanges is enabled!");
            Long autoDiscoverInterval = jBeatProperties.getRunner().getAutoDiscoverInterval();
            logger.info("AutoDiscoverInterval: {} !", autoDiscoverInterval);

            ScheduledJob autoDiscoverJob = new AutoDiscoverChangesJob(autoDiscoverInterval, periodicTaskService, scheduledJobService );
            autoDiscoverJob.schedule();
        }else {
            logger.info("AutoDiscoverChanges is disabled!");
        }

    }
}
