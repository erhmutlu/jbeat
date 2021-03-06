package org.erhmutlu.jbeat.service;

import org.assertj.core.api.Assertions;
import org.erhmutlu.jbeat.api.exceptions.JBeatException;
import org.erhmutlu.jbeat.api.exceptions.JBeatExceptionCodes;
import org.erhmutlu.jbeat.persistency.dao.PeriodicTaskDao;
import org.erhmutlu.jbeat.persistency.models.PeriodicTask;
import org.erhmutlu.jbeat.service.config.BaseTest;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by erhmutlu on 15/06/17.
 */
public class PeriodicTaskServiceTest extends BaseTest {

    @Autowired
    PeriodicTaskService periodicTaskService;

    @Autowired
    PeriodicTaskDao periodicTaskDao;

    @Test
    public void testGetActivesByTaskName() throws JBeatException {
        logger.info("testGetByTaskName begins");
        PeriodicTask periodicTask = randomPeriodicTaskInstance();
        periodicTask = periodicTaskDao.save(periodicTask);

        PeriodicTask fromDb = periodicTaskService.getTaskByName(periodicTask.getTaskName());
        Assert.assertNotNull(fromDb);
        Assert.assertEquals(periodicTask.getId(), fromDb.getId());

        Assertions.assertThatThrownBy(() -> periodicTaskService.getTaskByName("DUMMY")).isInstanceOf(JBeatException.class).hasFieldOrPropertyWithValue("code", JBeatExceptionCodes.PERIODIC_TASK_NOT_FOUND_BY_TASKNAME);
    }

    @Test
    public void testUpdateCrontabByTaskName() throws JBeatException {
        logger.info("testUpdateCrontabByTaskName begins");
        PeriodicTask periodicTask = randomPeriodicTaskInstance();
        periodicTask = periodicTaskDao.save(periodicTask);

        String newCrontab = "*/10 * * * * *";
        PeriodicTask fromDb = periodicTaskService.updateCrontabByTaskName(periodicTask.getTaskName(), newCrontab);
        Assert.assertNotNull(fromDb);
        Assert.assertEquals(periodicTask.getTaskName(), fromDb.getTaskName());
        Assert.assertEquals(newCrontab, fromDb.getCrontab());

        PeriodicTask fromDao = periodicTaskDao.findByTaskNameAndIsActiveIsTrue(periodicTask.getTaskName());
        Assert.assertEquals(newCrontab, fromDao.getCrontab());
    }

    @Test
    public void testCreateAndRemove() throws JBeatException {
        logger.info("testCreate begins");
        PeriodicTask random = randomPeriodicTaskInstance();
        PeriodicTask periodicTask = periodicTaskService.createPeriodicTask(
                random.getTaskName(),
                random.getQueue(),
                random.getCrontab(),
                random.getParams(),
                random.getDescription()
        );

        PeriodicTask fromDb = periodicTaskDao.findByTaskNameAndIsActiveIsTrue(random.getTaskName());
        Assert.assertNotNull(fromDb);
        Assert.assertEquals(periodicTask.getId(), fromDb.getId());

        Assertions.assertThatThrownBy(() -> periodicTaskService.createPeriodicTask(
                random.getTaskName(),
                random.getQueue(),
                random.getCrontab(),
                random.getParams(),
                random.getDescription()
        )).isInstanceOf(JBeatException.class).hasFieldOrPropertyWithValue("code", JBeatExceptionCodes.PERIODIC_TASK_ALREADY_EXIST);


        // test remove
        periodicTaskService.removeByTaskName(random.getTaskName());
        Assert.assertNull("task should not found in db", periodicTaskDao.findByTaskName(random.getTaskName()));
        Assertions.assertThatThrownBy(() -> periodicTaskService.removeByTaskName(random.getTaskName())).isInstanceOf(JBeatException.class).hasFieldOrPropertyWithValue("code", JBeatExceptionCodes.PERIODIC_TASK_NOT_FOUND_BY_TASKNAME);
    }

}
