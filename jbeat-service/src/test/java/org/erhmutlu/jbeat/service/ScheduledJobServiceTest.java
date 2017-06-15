package org.erhmutlu.jbeat.service;

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

import static org.hamcrest.core.Is.is;

/**
 * Created by erhmutlu on 08/06/17.
 */

public class ScheduledJobServiceTest extends BaseTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    ScheduledJobRegistry registry;

//    @MockBean
    @Autowired
    PeriodicTaskDao periodicTaskDao;

    @Autowired
    ScheduledJobService scheduledJobService;

    @Autowired
    RabbitWriterService rabbitWriterService;

    @Autowired
    PeriodicTaskService periodicTaskService;

    @Test
    public void testPutRegistry() throws InterruptedException {
        logger.info("Test begins");

        PeriodicTask periodicTask = randomPeriodicTaskInstance();
        periodicTaskDao.save(periodicTask);

        logger.info("{}", periodicTask);

        scheduledJobService.schedule(periodicTask);
        ScheduledJob scheduledJob = registry.get(periodicTask.getTaskName());
        Assert.assertEquals(periodicTask, scheduledJob.getTask());

        // PUT test when taskname is updated
        String oldTaskName = periodicTask.getTaskName();
        String newTaskName = "TEST";
        periodicTask.setTaskName(newTaskName);
        periodicTaskDao.save(periodicTask);

        scheduledJobService.schedule(periodicTask);
        scheduledJob = registry.get(periodicTask.getTaskName());
        Assert.assertEquals(newTaskName, scheduledJob.getTask().getTaskName());

        // new ScheduledJob should be instantiated in the registry and the old one should be removed from registry!
        Assert.assertNull(registry.get(oldTaskName));

    }

}
