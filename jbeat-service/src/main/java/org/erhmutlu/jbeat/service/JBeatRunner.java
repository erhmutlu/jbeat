package org.erhmutlu.jbeat.service;

import org.erhmutlu.jbeat.persistency.models.PeriodicTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by erhmutlu on 21/06/17.
 */

@Component
public class JBeatRunner implements CommandLineRunner {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    private PeriodicTaskService periodicTaskService;
    private ScheduledJobService scheduledJobService;

    @PersistenceConstructor
    public JBeatRunner(PeriodicTaskService periodicTaskService, ScheduledJobService scheduledJobService) {
        this.periodicTaskService = periodicTaskService;
        this.scheduledJobService = scheduledJobService;
    }

    @Override
    public void run(String... strings) throws Exception {
        logger.info("JBeatRunner run is starting...");

        List<PeriodicTask> actives = periodicTaskService.findAllActives();
        logger.debug("Active PeriodicTasks: {}", actives);

        actives.forEach(periodicTask -> {
            scheduledJobService.schedule(periodicTask);
        });

        logger.info("All tasks are scheduled!");
    }
}
