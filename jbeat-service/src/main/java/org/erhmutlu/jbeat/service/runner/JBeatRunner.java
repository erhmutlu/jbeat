package org.erhmutlu.jbeat.service.runner;

import org.erhmutlu.jbeat.api.JBeatProperties;
import org.erhmutlu.jbeat.service.PeriodicTaskService;
import org.erhmutlu.jbeat.service.ScheduledJobService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;


/**
 * Created by erhmutlu on 21/06/17.
 */

public abstract class JBeatRunner implements CommandLineRunner {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    protected PeriodicTaskService periodicTaskService;
    protected ScheduledJobService scheduledJobService;
    protected JBeatProperties jBeatProperties;

    public JBeatRunner(JBeatProperties jBeatProperties, PeriodicTaskService periodicTaskService, ScheduledJobService scheduledJobService) {
        this.jBeatProperties = jBeatProperties;
        this.periodicTaskService = periodicTaskService;
        this.scheduledJobService = scheduledJobService;
    }

}
