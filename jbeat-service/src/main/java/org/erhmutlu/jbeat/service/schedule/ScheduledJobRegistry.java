package org.erhmutlu.jbeat.service.schedule;

import org.erhmutlu.jbeat.api.Registry;
import org.erhmutlu.jbeat.persistency.models.PeriodicTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by erhmutlu on 10/06/17.
 */

public class ScheduledJobRegistry extends Registry<ScheduledJob> {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     *
     * Registers a PeriodicTask to Registry
     *
     * PeriodicTask.taskName is used for identifier in Registry.
     *
     * TaskName of a Periodic Task may change, In that situation, more than one schedulers would runs.
     * Duplicated ones are detected by PeriodicTask.id
     *
     * @param scheduledJob
     */
    public void put(ScheduledJob scheduledJob) {
        logger.info("ScheduledJobRegistry put(scheduledJob: {})", scheduledJob);
        PeriodicTask task = scheduledJob.getTask();

        // find duplicated ones
        Map<String, ScheduledJob> duplicates = registry.entrySet().stream().filter(map -> map.getValue().getTask().getId() == task.getId()).collect(Collectors.toMap(p -> p.getKey(), p -> p.getValue()));
        logger.info("Duplicated registries: {}", duplicates);
        for (String key: duplicates.keySet()) {
            // if here, there is a duplicated registry.
            ScheduledJob job = duplicates.get(key);

            logger.info("ScheduledJob: {} will be stopped and removed", job);

            // stopping the job is important to avoid undesired job runs.
            job.stop();
            super.remove(key);
            logger.info("ScheduledJob: {} is stopped and removed", job);
        }

        // put into registry safely.
        super.put(task.getTaskName(), scheduledJob);
    }

    @Override
    public ScheduledJob get(String taskName) {
        logger.info("ScheduledJobRegistry get(taskName: {})", taskName);
        return super.get(taskName);
    }
}
