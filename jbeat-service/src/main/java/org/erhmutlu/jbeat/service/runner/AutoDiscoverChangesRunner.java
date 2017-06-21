package org.erhmutlu.jbeat.service.runner;

import org.erhmutlu.jbeat.api.JBeatProperties;
import org.erhmutlu.jbeat.service.PeriodicTaskService;
import org.erhmutlu.jbeat.service.ScheduledJobService;
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

        if(jBeatProperties.isAutoDiscoverChangesEnabled()){
            logger.info("AutoDiscoverChanges is enabled!");
            Long autoDiscoverInterval = jBeatProperties.getAutoDiscoverInterval();
            logger.info("AutoDiscoverInterval: {} !", autoDiscoverInterval);

            // TODO initialize autodiscover changes job
        }

    }
}
