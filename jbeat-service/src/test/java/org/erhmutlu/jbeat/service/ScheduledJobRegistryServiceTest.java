package org.erhmutlu.jbeat.service;

import org.erhmutlu.jbeat.api.Registry;
import org.erhmutlu.jbeat.persistency.dao.PeriodicTaskDao;
import org.erhmutlu.jbeat.persistency.models.PeriodicTask;
import org.erhmutlu.jbeat.service.config.BaseTest;
import org.erhmutlu.jbeat.service.schedule.ScheduledJob;
import org.erhmutlu.jbeat.service.schedule.ScheduledJobRegistry;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by erhmutlu on 08/06/17.
 */

public class ScheduledJobRegistryServiceTest extends BaseTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    ScheduledJobRegistry registry;

    @Autowired
    PeriodicTaskDao periodicTaskDao;

    @Autowired
    ScheduledJobRegistryService scheduledJobRegistryService;

    @Test
    public void testPut() throws InterruptedException {
        logger.info("Test begins");

        PeriodicTask periodicTask = randomPeriodicTaskInstance();
        periodicTaskDao.save(periodicTask);

        logger.info("{}", periodicTask);

        scheduledJobRegistryService.schedule(periodicTask);
        ScheduledJob scheduledJob = registry.get(periodicTask.getTaskName());
        Assert.assertEquals(scheduledJob.getTask(), periodicTask);

        // PUT test when taskname is updated
        String oldTaskName = periodicTask.getTaskName();
        periodicTask.setTaskName("TEST");
        periodicTaskDao.save(periodicTask);

        scheduledJobRegistryService.schedule(periodicTask);
        scheduledJob = registry.get(periodicTask.getTaskName());

        // new ScheduledJob should be instantiated in the registry and the old one should be removed from registry!
        Assert.assertNull(registry.get(oldTaskName));

    }

}
