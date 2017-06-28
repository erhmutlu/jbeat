package org.erhmutlu.jbeat.service.schedule;

import org.erhmutlu.jbeat.api.ScheduledJob;
import org.erhmutlu.jbeat.persistency.models.PeriodicTask;
import org.erhmutlu.jbeat.service.PeriodicTaskService;
import org.erhmutlu.jbeat.service.ScheduledJobService;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by erhmutlu on 28/06/17.
 */
public class AutoDiscoverChangesJob extends AbstractJob {

    private Long rate;
    private PeriodicTaskService periodicTaskService;
    private ScheduledJobService scheduledJobService;


    public AutoDiscoverChangesJob(Long rate, PeriodicTaskService periodicTaskService, ScheduledJobService scheduledJobService) {
        this.rate = rate;
        this.periodicTaskService = periodicTaskService;
        this.scheduledJobService = scheduledJobService;
    }

    /**
     * Discovers changes on scheduled PeriodicTask objects in ScheduledJobRegistry!
     * UPDATE or DELETE
     * <p>
     * Does not schedules non-registered ones on db !
     */
    @Override
    public void run() {
        logger.info("AutoDiscoverChangesJob is running");

        List<ScheduledJob> registeredTasks = scheduledJobService.getAllRegisteredJobs();
        List<PeriodicTask> activesOnDb = periodicTaskService.getAllActives();

        registeredTasks.forEach((job) -> {
            logger.info("Registered PeriodicTask.id: {}", job.getTask().getId());
            Optional<PeriodicTask> filtered = activesOnDb.stream().filter(p -> p.getId().equals(job.getTask().getId())).findAny();
            if (filtered.isPresent()) {
                logger.info("Exist on db!");
                PeriodicTask fromDb = filtered.get();
                if (fromDb.getActive()) {
                    scheduledJobService.schedule(fromDb);
                    return;
                }
            }
            logger.info("Not exist nor Not active on db!");
            scheduledJobService.disable(job.getTask().getTaskName());
        });
    }

    @Override
    public void schedule() {
        scheduler.scheduleAtFixedRate(rate, this);
    }

    @Override
    public String toString() {
        return "AutoDiscoverChangesJob{" +
                "rate=" + rate +
                ", periodicTaskService=" + periodicTaskService +
                ", scheduledJobService=" + scheduledJobService +
                '}';
    }
}
