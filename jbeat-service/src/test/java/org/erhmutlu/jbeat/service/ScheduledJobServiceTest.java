package org.erhmutlu.jbeat.service;

import org.erhmutlu.jbeat.api.ScheduledJob;
import org.erhmutlu.jbeat.persistency.dao.PeriodicTaskDao;
import org.erhmutlu.jbeat.persistency.models.PeriodicTask;
import org.erhmutlu.jbeat.service.config.BaseTest;
import org.erhmutlu.jbeat.service.schedule.RabbitJob;
import org.erhmutlu.jbeat.service.schedule.ScheduledJobRegistry;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

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
    public void testMe() throws InterruptedException {
        logger.info("Test begins");

        PeriodicTask periodicTask = randomPeriodicTaskInstance();
        periodicTaskDao.save(periodicTask);

        logger.info("{}", periodicTask);

        List<ScheduledJob> registeredJobs = scheduledJobService.getAllRegisteredJobs();
        Assert.assertEquals(0, registeredJobs.size());

        scheduledJobService.schedule(periodicTask);
        ScheduledJob rabbitJob = registry.get(periodicTask.getTaskName());
        Assert.assertEquals(periodicTask, rabbitJob.getTask());

        registeredJobs = scheduledJobService.getAllRegisteredJobs();
        Assert.assertEquals(1, registeredJobs.size());

        // PUT test when taskname is updated
        String oldTaskName = periodicTask.getTaskName();
        String newTaskName = "TEST";
        periodicTask.setTaskName(newTaskName);
        periodicTaskDao.save(periodicTask);

        scheduledJobService.schedule(periodicTask);
        rabbitJob = registry.get(periodicTask.getTaskName());
        Assert.assertEquals(newTaskName, rabbitJob.getTask().getTaskName());

        // new RabbitJob should be instantiated in the registry and the old one should be removed from registry!
        Assert.assertNull(registry.get(oldTaskName));
        registeredJobs = scheduledJobService.getAllRegisteredJobs();
        Assert.assertEquals(1, registeredJobs.size());

        //Disable
        scheduledJobService.disable(periodicTask.getTaskName());
        Assert.assertNull(registry.get(periodicTask.getTaskName()));
    }

}
