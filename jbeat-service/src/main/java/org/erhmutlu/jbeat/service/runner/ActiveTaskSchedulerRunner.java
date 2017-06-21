package org.erhmutlu.jbeat.service.runner;

import org.erhmutlu.jbeat.persistency.models.PeriodicTask;
import org.erhmutlu.jbeat.service.PeriodicTaskService;
import org.erhmutlu.jbeat.service.ScheduledJobService;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by erhmutlu on 21/06/17.
 */
@Component
public class ActiveTaskSchedulerRunner extends JBeatRunner {

    @PersistenceConstructor
    public ActiveTaskSchedulerRunner(PeriodicTaskService periodicTaskService, ScheduledJobService scheduledJobService) {
        super(periodicTaskService, scheduledJobService);
    }

    @Override
    public void run(String... strings) throws Exception {
        logger.info("ActiveTaskSchedulerRunner run is starting...");

        List<PeriodicTask> actives = periodicTaskService.getAllActives();
        logger.debug("Active PeriodicTasks: {}", actives);

        actives.forEach(periodicTask -> {
            scheduledJobService.schedule(periodicTask);
        });

        logger.info("All tasks are scheduled!");
    }
}
