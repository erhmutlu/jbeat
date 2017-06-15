package org.erhmutlu.jbeat.service;

import org.assertj.core.api.Assertions;
import org.erhmutlu.jbeat.api.exceptions.JBeatException;
import org.erhmutlu.jbeat.persistency.dao.PeriodicTaskDao;
import org.erhmutlu.jbeat.persistency.models.PeriodicTask;
import org.erhmutlu.jbeat.service.config.BaseTest;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by erhmutlu on 15/06/17.
 */
public class PeriodicTaskServiceTest extends BaseTest {

    Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired
    PeriodicTaskService periodicTaskService;

    @Autowired
    PeriodicTaskDao periodicTaskDao;

    @Test
    public void testGetByTaskName() throws JBeatException {
        logger.info("testGetByTaskName begins");
        PeriodicTask periodicTask = randomPeriodicTaskInstance();
        periodicTask = periodicTaskDao.save(periodicTask);

        PeriodicTask fromDb = periodicTaskService.getByTaskName(periodicTask.getTaskName());
        Assert.assertNotNull(fromDb);
        Assert.assertEquals(periodicTask.getId(), fromDb.getId());

        Assertions.assertThatThrownBy(() -> periodicTaskService.getByTaskName("DUMMY")).isInstanceOf(JBeatException.class);
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

        PeriodicTask fromDao = periodicTaskDao.findByTaskName(periodicTask.getTaskName());
        Assert.assertEquals(newCrontab, fromDao.getCrontab());
    }

}
