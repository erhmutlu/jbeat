package org.erhmutlu.jbeat.service;

import org.erhmutlu.jbeat.persistency.models.PeriodicTask;
import org.erhmutlu.jbeat.service.schedule.ScheduledJob;

/**
 * Created by erhmutlu on 10/06/17.
 */
public interface ScheduledJobRegistryService {

    ScheduledJob schedule(PeriodicTask periodicTask);
}
